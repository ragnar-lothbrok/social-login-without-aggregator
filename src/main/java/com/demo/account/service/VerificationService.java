package com.demo.account.service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.ws.rs.core.MediaType;

import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.demo.account.dao.AccountDao;
import com.demo.account.dao.VerifyUserDao;
import com.demo.account.model.Account;
import com.demo.account.model.VerifyUser;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;

@Service
public class VerificationService {

	final static Logger logger = LoggerFactory.getLogger(VerificationService.class);

	@Autowired
	private VerifyUserDao verifyUserDao;

	@Autowired
	private AccountDao accountDao;
	
	@Value("${mailgun.fromEmail.address}")
    private String fromEmail;

    @Value("${mailgun.apikey}")
    private String apiKey;

    @Value("${mailgun.mailhost}")
    private String mailHost;
    
	@Autowired
	private VelocityEngine velocityEngine;

	public boolean verifyUser(String code) {
		VerifyUser verifyUser = verifyUserDao.findVerifyUserByCode(code);
		if (verifyUser != null) {
			Account account = accountDao.findOne(verifyUser.getUserId());
			if (account != null) {
				if (account.getIsVerified() != null && account.getIsVerified()) {
					return true;
				} else {
					account.setIsVerified(true);
					accountDao.save(account);
				}
			}
		}
		return false;
	}

	public void sendMailByMailGun(String emailId, String url) {
		try {
			Client client = Client.create();
			client.addFilter(new HTTPBasicAuthFilter("api", apiKey));
			WebResource webResource = client.resource(mailHost);

			MultivaluedMapImpl formData = new MultivaluedMapImpl();
			formData.add("from", "Lembas <" + fromEmail + ">");
			formData.add("to", emailId);
			formData.add("subject", "Verify your mail");
			Map<String, Object> map  = new HashMap<String, Object>();
			map.put("url", url);
			//Can read from database itself
			String mailBody = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "spam_test.vm", "UTF-8", map);
			mailBody = mailBody.replaceAll("898", url);
			formData.add("html", mailBody);

			formData.add("o:tracking", true);
			formData.add("o:tracking-opens", true);
			formData.add("o:tracking-clicks", "htmlonly");

			logger.info("Send Mail details : " + webResource.type(MediaType.APPLICATION_FORM_URLENCODED).post(ClientResponse.class, formData));
		} catch (Exception e) {
			logger.error("Exception occured : {}", e);
		} finally {

		}
	}

	public VerifyUser saveVerifyUser(Account account) {
		VerifyUser verifyUser = new VerifyUser();
		verifyUser.setUserId(account.getId());
		verifyUser.setCode(UUID.randomUUID().toString());
		Calendar cal = Calendar.getInstance();
		verifyUser.setCreatedDate(cal.getTime());
		cal.add(Calendar.DAY_OF_WEEK, 3);
		verifyUser.setExpiryDate(cal.getTime());
		sendMailByMailGun(account.getEmailId(), "http://localhost:8888/verify?code="+verifyUser.getCode());
		return verifyUserDao.save(verifyUser);
	}
}
