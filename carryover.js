var prefix = "https://cosplayerashley.blogspot.com/p/";
var url = window.location.href;
var urlObject = new URL(url);
var suffix = urlObject.searchParams.get("suffix");

if (typeof handle === 'undefined' && suffix != null) 
{
     var handle = suffix;
}

var source = document.getElementsByTagName('html')[0].innerHTML;
document.getElementsByTagName('html')[0].innerHTML = source.replace("${handle}", handle);

console.log("Replaced handle with " + handle);

// var foundIndex = source.indexOf("favourite Cosplayer now in a short story!");

// if(foundIndex > -1)
// {
// 	handle = url.replace(prefix, "").replace(".html", "");

// 	nextlink1 = document.getElementById("nextlink1");
// 	nextlink2 = document.getElementById("nextlink2");

// 	if(nextlink1 != null)
// 	{
// 		nextlink1.href = nextlink1.href.replace("${handle}", handle);
// 	}

// 	if(nextlink2 != null)
// 	{
// 		nextlink2.href = nextlink2.href.replace("${handle}", handle);
// 	}
// }

var url = "https://www.instagram.com/" + handle + "/?__a=1";

$.getJSON(url, function(response) 
{
    var full_name = document.getElementById("full_name");
    var about = document.getElementById("about");
    var profile_picture = document.getElementById("profile_picture");
    var profile_pic_url_hd = document.getElementById("profile_picture_hd");
      
    console.log(response);
 
    if(full_name != null && typeof response.graphql.user.full_name !== 'undefined')
    {
        full_name.innerHTML = response.graphql.user.full_name.toUpperCase();
    }
     
    if(about != null && typeof response.graphql.user.biography !== 'undefined')
    {
        about.innerHTML = response.graphql.user.biography;
    }

    if(profile_picture != null && typeof response.graphql.user.profile_pic_url !== 'undefined')
    {
        profile_picture.src = response.graphql.user.profile_pic_url;
    }
    
    if(profile_pic_url_hd != null && typeof response.graphql.user.profile_pic_url_hd !== 'undefined')
    {
        profile_pic_url_hd.src = response.graphql.user.profile_pic_url_hd;
    }
});

// Poster logic
const poster = document.getElementById("poster");
const theme = document.getElementById("theme");

function changeImg(id)
{
    var inHTML = document.getElementById(id).innerHTML;    
    poster.innerHTML = inHTML;
}

if(theme != null)
{
     theme.play();
}

if(poster != null)
{
     for(var i = 3; i >= 1; i --)
     {
          const id = "p" + i.toString();
          setTimeout(function(){ changeImg(id); }, (3 - i) * 3000);
     }
}
