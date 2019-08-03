var prefix = "https://cosplayerashley.blogspot.com/p/";
var url = window.location.href;
alert(url);

if (typeof handle === 'undefined') 
{
     var handle = "";
    
     if(url.indexOf(prefix) > -1)
     {
          handle = url.replace(prefix, "").replace(".html", "");
     }
}

alert(handle);

var source = document.getElementsByTagName('html')[0].innerHTML;
var foundIndex = source.indexOf("favourite Cosplayer now in a short story!");

if(foundIndex > -1)
{
	handle = url.replace(prefix, "").replace(".html", "");

	nextlink1 = document.getElementById("nextlink1");
	nextlink2 = document.getElementById("nextlink2");

	if(nextlink1 != null)
	{
		nextlink1.href = nextlink1.href.replace("${handle}", handle);
	}

	if(nextlink2 != null)
	{
		nextlink2.href = nextlink2.href.replace("${handle}", handle);
	}
}
