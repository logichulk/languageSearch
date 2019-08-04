var url = "https://www.instagram.com/" + handle + "/?__a=1";

alert(url);

$.getJSON(url, function(data) 
{
    var profile_picture = document.getElementById("profile_picture");
    
    if(profile_picture != null)
    {
        profile_picture.innerHTML = "<img src='" + data.data.profile_picture + "' />";
    }
});
