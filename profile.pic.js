<script src="http://codeorigin.jquery.com/jquery-2.0.3.min.js"></script><script type="text/javascript">
var handle = "bipolar.om";
var url = "https://www.instagram.com/" + handle + "/?__a=1";
$.getJSON(url, function(data) {
    $("body").append("<img src='"+data.data.profile_picture+"' />");
});
</script>
