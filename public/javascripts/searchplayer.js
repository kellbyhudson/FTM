$(document).ready(function(){
  $("#searchname").on("keyup", function() {
    var value = $(this).val().toLowerCase();
    $("#mySource h4").filter(function() {
      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
    });
  });
});