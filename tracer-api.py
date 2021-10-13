from flask import Flask, render_template, request
from flask_mysqldb import MySQL
import json
from flask import Flask, request
from flask import jsonify
import os
import mysql.connector as mariadb

app = Flask(__name__)
app.config['JSONIFY_PRETTYPRINT_REGULAR'] = True

con = mariadb.connect(user='radmin',password='radpassword',database='tracer',host='127.0.0.1',auth_plugin='mysql_native_password')

@app.route('/', methods=['GET'])
def index():
    
        
        cursor = con.cursor()
        cursor.execute("SELECT * FROM records")
        result = cursor.fetchall()
        list = []
        for row in result:
            list.append({"id" : row[0],"lat" : row[1],"lon" : row[2],"timestamp" : row[3], "category" : row[4]})
        return jsonify(list)
    
if __name__ == "__main__":
   app.secret_key = os.urandom(12)
   app.run(debug=False,host='0.0.0.0',port=5000)
