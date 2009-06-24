// Mozilla SOAP/Google API functions
// Author: Scott Andrew LePera (scott@scottandrew.com)

var URI = "http://api.google.com/search/beta2";

function handleResponse(response,call,error)
{
    if (error != 0)
    { 
        // service failure
        alert("Service failure");
        return false;
    } else 
    {
        // process result
        // check for fault
        var fault = response.fault; 
        if (fault != null) { 
            var namespace = fault.faultNamespace; 
            var name = fault.faultCode; 
            var summary = fault.faultString; 
            var actorURI = fault.actorURI; 
            alert("SOAP Fault:\n\n"  + summary);
            return false;
        } else 
        {
            return response;
        }
    }
}

function spellingSuggestion(phrase,key,callback)
{
    var p = [];
    p[p.length] = new SOAPParameter(key,"key");
    p[p.length] = new SOAPParameter(phrase,"phrase");
    callGoogle("doSpellingSuggestion",p,callback); 
}

function googleSearch(q,start,maxResults,filter,restrict,safeSearch,lr,ie,oe,key,callback)
{
    var p = [];
    p[p.length] = new SOAPParameter(key,"key");
    p[p.length] = new SOAPParameter(q,"q");
    p[p.length] = new SOAPParameter(start,"start");
    p[p.length] = new SOAPParameter(maxResults,"maxResults");
    p[p.length] = new SOAPParameter(filter,"filter");
    p[p.length] = new SOAPParameter(restrict,"restrict");
    p[p.length] = new SOAPParameter(safeSearch,"safeSearch");
    p[p.length] = new SOAPParameter(lr,"lr");
    p[p.length] = new SOAPParameter(ie,"ie");
    p[p.length] = new SOAPParameter(oe,"oe");
    callGoogle("doGoogleSearch",p,callback);   
}

function callGoogle(action,p,callback)
{
    try
    {
        netscape.security.PrivilegeManager.enablePrivilege("UniversalBrowserRead");
    } catch (e)
    {
        alert(e);
    }
    var soapCall = new SOAPCall();
    soapCall.transportURI = URI;
    soapCall.encode(0, action, "urn:GoogleSearch", 0, null, p.length, p);
    soapCall.asyncInvoke(
        function (response,call,error)
        {
            p = handleResponse(response,call,error);
            callback(p);
        }
    );
}