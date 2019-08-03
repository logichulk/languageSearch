var prefix = "https://cosplayerashley.blogspot.com/p/";
var url = window.location.href;
var urlObject = new URL(url);
var suffix = urlObject.searchParams.get("suffix");

alert("suffix = " + suffix);

if (typeof handle === 'undefined' && suffix != null) 
{
     var handle = suffix;
}

alert("handle = " + handle);

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
