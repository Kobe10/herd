let renderHtml = (function () {
  function login() {
    var btn = $('.btn'),
      userNameValue, passWordValue;
    btn.click(function () {
      userNameValue = $(" input[ type='text' ] ").val();
      passWordValue = $(" input[ type='password' ] ").val();
      let infoData = {
        userName: userNameValue,
        userPassword: passWordValue
      }
      if (userNameValue !== 'admin' || passWordValue !== 'admin') {
        alert('请输入正确的用户名或密码')
      } else {
        $.ajax({
          url: 'http://211.159.176.138:8085/herd/web/user/login',
          type: 'post',
          data: JSON.stringify(infoData),
          headers: {
            'content-type': 'application/json'
          },
          success: function (res) {
            if (res.code == 200) {
              document.cookie = "name=admin"
              window.location.href = './information.html'
            }

          },
          error: function (res) {
            alert(res)
          }
        })
      }
    })
  }
  return {
    init() {
      login()
    }
  }
})()
renderHtml.init()