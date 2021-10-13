from flask import jsonify
from flask import Flask,request
import pyotp
import pyqrcode
from io import BytesIO
from asyncio import streams
import os

app = Flask(__name__)
secret = pyotp.random_base32()
name = 'al1eum'
issuer_name = 'al1enum.local'
uri = pyotp.totp.TOTP(secret).provisioning_uri(name, issuer_name)
print (uri)
totp = pyotp.TOTP(secret)

@app.route('/', methods=['GET'])
def qrcode():

    url = pyqrcode.create(uri)
    stream = BytesIO()
    url.svg(stream, scale=5)
    return stream.getvalue(),200,{
     'Content-Type': 'image/svg+xml',
     'Cache-Control': 'no-cache, no-store, must-revalidate',
     'Pragma': 'no-cache',
     'Expires': '0'    
    }

@app.route('/verify',methods=['GET'])
def otp():

   code = totp.now()
   if totp.verify(code):
       return "<b>Verified<b>"
    
if __name__ == "__main__":
   app.secret_key = os.urandom(12)
   app.run(debug=False,host='0.0.0.0',port=5000)
