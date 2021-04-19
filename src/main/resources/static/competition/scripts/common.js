window.onload = function () {
  window.addEventListener('resize', computedFont, false);

  function computedFont() {
    var deviceW = document.documentElement.clientWidth;
    document.documentElement.style.fontSize = deviceW / 750 * 100 + 'px';
  }
  computedFont();
}
var host = "http://47.114.135.244/herd"  
//var host = "http://211.159.176.138:8085/herd" //测试地址


//获取参数
function getQueryVariable(variable) {
  var query = window.location.search.substring(1);
  var vars = query.split("&");
  for (var i = 0; i < vars.length; i++) {
    var pair = vars[i].split("=");
    if (pair[0] == variable) {
      return pair[1];
    }
  }
  return (false);
}


//手机号正则验证
function checkPhone(phone) {
  var tc = $('.tc')
  if (!(/^1[3456789]\d{9}$/.test(phone))) {
    tc.show()
    tc.html('手机号有误，请重新输入')
    setTimeout(function () {
      tc.hide()
    }, 3000)
    return false;
  }
}


//验证姓名
function checkName(name) {
  var tc = $('.tc')
  if (!(/^(([a-zA-Z+\.?\·?a-zA-Z+]{2,30}$)|([\u4e00-\u9fa5+\·?\u4e00-\u9fa5+]{2,30}$))/.test(name))) {
    tc.show()
    tc.html('姓名输入有误，请重新输入')
    setTimeout(function () {
      tc.hide()
    }, 3000)
    return false;
  }
}



//判断ios 安卓机型
const ua = typeof window === 'object' ? window.navigator.userAgent : '';
let _isIOS = -1;
let _isAndroid = -1;

function isIOS() {
  if (_isIOS === -1) {
    _isIOS = /iPhone|iPod|iPad/i.test(ua) ? 1 : 0;
  }
  return _isIOS === 1;
}

function isAndroid() {
  if (_isAndroid === -1) {
    _isAndroid = /Android/i.test(ua) ? 1 : 0;
  }
  return _isAndroid === 1;
}




// var key  = CryptoJS.enc.Latin1.parse('abcdef0123456789');
// var iv   = CryptoJS.enc.Latin1.parse('abcdef0123456789');

// 加密
function EncryptData(data) {
  var srcs = CryptoJS.enc.Utf8.parse(data);
  var encrypted = CryptoJS.AES.encrypt(srcs, key, {
    mode: CryptoJS.mode.ECB,
    padding: CryptoJS.pad.Pkcs7
  });
  return encrypted.toString();
}

// 解密
function DecryptData(data) {
  var stime = new Date().getTime();
  var decrypt = CryptoJS.AES.decrypt(data, key, {
    mode: CryptoJS.mode.ECB,
    padding: CryptoJS.pad.Pkcs7
  });
  var result = JSON.parse(CryptoJS.enc.Utf8.stringify(decrypt).toString());
  var etime = new Date().getTime();
  console.log("DecryptData Time:" + (etime - stime));
  return result;
}