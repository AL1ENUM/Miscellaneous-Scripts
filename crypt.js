var CryptoJS = require("crypto-js");
var encrypted = CryptoJS.AES.encrypt("myrandompassword", "SecretPassphraseMomentum");
console.log(encrypted.toString());
var decrypted = CryptoJS.AES.decrypt(encrypted, "SecretPassphraseMomentum");
console.log(decrypted);
