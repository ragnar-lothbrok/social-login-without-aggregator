# social-login-without-aggregator
Here we won't be needing any aggregator.

http://localhost:8081/uaa/signup

{ "emailId": "namo@gmail.com", "password":"ddfdf", "ssoProvider": "app", "firstName": "Narendra", "lastName": "Modi", "country": "India", "gender": "male", "phoneNumber": "9999999999", "dob":"1991-11-17" }


Postman :

URL : http://localhost:8888/uaa/oauth/token
Form Fields : 

x-wwww-form-urlencoded
username : namo@gmail.com
password : ddfdf
grant_type : password
client_id : acme

Header [Basic Authentication]:

username : aceme
password : acemesecret

equivalent to Basic YWNtZTphY21lc2VjcmV0

Response :

{
  "access_token": "eyJhbGciOiJSUzI1NiJ9.eyJleHAiOjE0OTgxMjY4MjQsInVzZXJfbmFtZSI6IjI1OTQ4OSIsImF1dGhvcml0aWVzIjpbIlJPTEVfQVVUSEVOVElDQVRFRF9VU0VSIl0sImp0aSI6ImY2Yjg3ZmVmLWQyZDMtNGEzNC1iNGMxLTU1YjFhNTA5ODExMiIsImNsaWVudF9pZCI6ImFjbWUiLCJzY29wZSI6WyJvcGVuaWQiXX0.m4F6hVHFHhDj9vnjpvv4xzkdwd9bBaOgSvTw5yn2vdv8DwQva9Bnrzkn9nTS8qprYDejptv1rSgfQcsadhcD_Dqo4A9LTH56BCKLzyD4VwdPcfdSfsPQ0zLGi_IARC4mXt34ykVDzl5yiO89g6FbUobg0SqjkRMg1-Kr2ZZXGg_RsXNDFqWQ2Rdpr26eOIfcYOvv3i-JuWEqqeJG3MKw6CQEBgCz9Rb3pyY9iRnkEzXj237AwEJCCIy5fyJcPLLnVolGr89Fq81Yks0G9u6FHIAWSPBuuHed1rAbpTHntpZG_YYDGlCtWUASaVhBw-7VrKdnJTJk1hVwZ9wacEl4BQ",
  "token_type": "bearer",
  "refresh_token": "eyJhbGciOiJSUzI1NiJ9.eyJ1c2VyX25hbWUiOiIyNTk0ODkiLCJzY29wZSI6WyJvcGVuaWQiXSwiYXRpIjoiZjZiODdmZWYtZDJkMy00YTM0LWI0YzEtNTViMWE1MDk4MTEyIiwiZXhwIjoxNDY5MTgyODI0LCJhdXRob3JpdGllcyI6WyJST0xFX0FVVEhFTlRJQ0FURURfVVNFUiJdLCJqdGkiOiIzYjQ2ODU5Zi1lYjkyLTQ1MzEtYjVmMy05YWNiNzk4OTkzZGUiLCJjbGllbnRfaWQiOiJhY21lIn0.R5eFkA0fwVjYaJd3wBW558qyM_4Khn0m2yFIca5W7CNOwQhVBulT7wDb9KzCty2uDk0V5oLvwmttzxL49u9FhcSif-6rGA5qqhwZcXY_CNHTK5sqt02Vs6lXA628yRyjh0Ce-0_M6aeiqwe5GZGI2qYKSAdYUIPSMJUK8vIpC-zYiCI7CMESsPyknPgSMySeiHj3V1I8-QngMksJvIxzZ_l3fMRfPCeUVaqGAxAhfB3ayFSjI39_k7ZgUSS1o5FESJRV9HvShELgJ5U9vtdxSbkIXo2SkSYXdUfCL53xMilUFx3o8Q2nic14JqufurUhyD5YdM5lbzvxG-SrFsmh7Q",
  "expires_in": 31535999,
  "scope": "openid",
  "jti": "f6b87fef-d2d3-4a34-b4c1-55b1a5098112"
}

How to get user details :

http://localhost:8888/uaa/userDetails

header :
Authentication Bearer eyJhbGciOiJSUzI1NiJ9.eyJleHAiOjE0OTgxMjY4MjQsInVzZXJfbmFtZSI6IjI1OTQ4OSIsImF1dGhvcml0aWVzIjpbIlJPTEVfQVVUSEVOVElDQVRFRF9VU0VSIl0sImp0aSI6ImY2Yjg3ZmVmLWQyZDMtNGEzNC1iNGMxLTU1YjFhNTA5ODExMiIsImNsaWVudF9pZCI6ImFjbWUiLCJzY29wZSI6WyJvcGVuaWQiXX0.m4F6hVHFHhDj9vnjpvv4xzkdwd9bBaOgSvTw5yn2vdv8DwQva9Bnrzkn9nTS8qprYDejptv1rSgfQcsadhcD_Dqo4A9LTH56BCKLzyD4VwdPcfdSfsPQ0zLGi_IARC4mXt34ykVDzl5yiO89g6FbUobg0SqjkRMg1-Kr2ZZXGg_RsXNDFqWQ2Rdpr26eOIfcYOvv3i-JuWEqqeJG3MKw6CQEBgCz9Rb3pyY9iRnkEzXj237AwEJCCIy5fyJcPLLnVolGr89Fq81Yks0G9u6FHIAWSPBuuHed1rAbpTHntpZG_YYDGlCtWUASaVhBw-7VrKdnJTJk1hVwZ9wacEl4BQ

{
  "userDetails": {
    "id": 259489,
    "emailId": "abcd@gmail.com",
    "firstName": "Narendra",
    "lastName": "Modi",
    "gender": "male",
    "ssoProvider": "app",
    "dob": "1991-11-17",
    "phoneNumber": "9999999999",
    "isActive": 1,
    "country": "India"
  },
  "status": true
}

Please let me know if it is not working for you.






