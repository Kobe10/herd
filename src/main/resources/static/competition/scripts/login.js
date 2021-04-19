var competitionCode = ''
if (!sessionStorage.getItem('competitionCode')) {
  window.location.replace('http://zj.ifeng.com/special/zjscdhsjjs1/index_home.shtml')
}
//competitionCode = sessionStorage.getItem('competitionCode')


// $.ajax({
//   url: host + "/api/base/login",
//   type: "post",
//   contentType: "application/json",
//   dataType: "JSON",
//   data: JSON.stringify({
//     "competitionCode": competitionCode
//   }),
//   success: function (res) {
//     if (res.code == 200) {
//       console.log(res.data)
//     } else {

//     }
//   }
// })