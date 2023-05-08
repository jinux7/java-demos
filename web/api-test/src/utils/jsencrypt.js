import JSEncrypt from 'jsencrypt/bin/jsencrypt.min'
import CryptoJS from 'crypto-js'

// 密钥对生成 http://web.chacuo.net/netrsakeypair

// const publicKey = 'MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAKoR8mX0rGKLqzcWmOzbfj64K8ZIgOdH\n' +
//   'nzkXSOVOZbFu/TJhZ7rFAN+eaGkl3C4buccQd/EjEsj9ir7ijT7h96MCAwEAAQ=='

// const privateKey = 'MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAqhHyZfSsYourNxaY\n' +
//   '7Nt+PrgrxkiA50efORdI5U5lsW79MmFnusUA355oaSXcLhu5xxB38SMSyP2KvuKN\n' +
//   'PuH3owIDAQABAkAfoiLyL+Z4lf4Myxk6xUDgLaWGximj20CUf+5BKKnlrK+Ed8gA\n' +
//   'kM0HqoTt2UZwA5E2MzS4EI2gjfQhz5X28uqxAiEA3wNFxfrCZlSZHb0gn2zDpWow\n' +
//   'cSxQAgiCstxGUoOqlW8CIQDDOerGKH5OmCJ4Z21v+F25WaHYPxCFMvwxpcw99Ecv\n' +
//   'DQIgIdhDTIqD2jfYjPTY8Jj3EDGPbH2HHuffvflECt3Ek60CIQCFRlCkHpi7hthh\n' +
//   'YhovyloRYsM+IS9h/0BzlEAuO0ktMQIgSPT3aFAgJYwKpqRYKlLDVcflZFCKY7u3\n' +
//   'UP8iWi1Qw0Y='

// // 加密
// export function encrypt(txt) {
//   const encryptor = new JSEncrypt()
//   encryptor.setPublicKey(publicKey) // 设置公钥
//   return encryptor.encrypt(txt) // 对数据进行加密
// }

// // 解密
// export function decrypt(txt) {
//   const encryptor = new JSEncrypt()
//   encryptor.setPrivateKey(privateKey) // 设置私钥
//   return encryptor.decrypt(txt) // 对数据进行解密
// }

const secretKey = '1234567890abcdefg';

// 加密
export function encrypt(str) {
  var key = CryptoJS.enc.Base64.parse(secretKey);
  var iv = CryptoJS.enc.Base64.parse(secretKey);
   var encrypted = CryptoJS.AES.encrypt(str,  key, {
      iv: iv,
      padding: CryptoJS.pad.Pkcs7,
      mode: CryptoJS.mode.CBC
  });
  var cipherText = encrypted.ciphertext.toString();
  return cipherText;
}

// 解密
export function decrypt(encrypted) {
  var key = CryptoJS.enc.Base64.parse(secretKey);
  var iv = CryptoJS.enc.Base64.parse(secretKey);
  var cipherTextHexStr = CryptoJS.enc.Hex.parse(encrypted);
  var cipherTextBase64Str = CryptoJS.enc.Base64.stringify(cipherTextHexStr);
  var decrypted = CryptoJS.AES.decrypt(cipherTextBase64Str, key, {
              iv: iv,
              padding: CryptoJS.pad.Pkcs7,
              mode: CryptoJS.mode.CBC
          });
  return decrypted.toString(CryptoJS.enc.Utf8);
}
