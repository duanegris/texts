
  
  

  


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
  <head>
    <meta http-equiv="content-type" content="text/html;charset=UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="chrome=1">
        <title>src/main/java/codekata19/CodeKata19.java at master from cchandler's codekata19-mapreduce - GitHub</title>
    <link rel="search" type="application/opensearchdescription+xml" href="/opensearch.xml" title="GitHub" />
    <link rel="fluid-icon" href="http://github.com/fluidicon.png" title="GitHub" />

    <link href="http://assets3.github.com/stylesheets/bundle_common.css?25e168045a8f6c32ad0d68f405d75e0c4b8a76f1" media="screen" rel="stylesheet" type="text/css" />
<link href="http://assets3.github.com/stylesheets/bundle_github.css?25e168045a8f6c32ad0d68f405d75e0c4b8a76f1" media="screen" rel="stylesheet" type="text/css" />

    <script type="text/javascript" charset="utf-8">
      var GitHub = {}
      var github_user = null
      
    </script>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js" type="text/javascript"></script>
    <script src="http://assets1.github.com/javascripts/bundle_common.js?25e168045a8f6c32ad0d68f405d75e0c4b8a76f1" type="text/javascript"></script>
<script src="http://assets2.github.com/javascripts/bundle_github.js?25e168045a8f6c32ad0d68f405d75e0c4b8a76f1" type="text/javascript"></script>

        <script type="text/javascript" charset="utf-8">
      GitHub.spy({
        repo: "cchandler/codekata19-mapreduce"
      })
    </script>

    
  
    
  

  <link href="http://github.com/cchandler/codekata19-mapreduce/commits/master.atom" rel="alternate" title="Recent Commits to codekata19-mapreduce:master" type="application/atom+xml" />

        <meta name="description" content="MapReduce implementation of Code Kata 19" />
    <script type="text/javascript">
      GitHub.nameWithOwner = GitHub.nameWithOwner || "cchandler/codekata19-mapreduce";
      GitHub.currentRef = "master";
    </script>
  

            <script type="text/javascript">
      var _gaq = _gaq || [];
      _gaq.push(['_setAccount', 'UA-3769691-2']);
      _gaq.push(['_trackPageview']);
      (function() {
        var ga = document.createElement('script');
        ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
        ga.setAttribute('async', 'true');
        document.documentElement.firstChild.appendChild(ga);
      })();
    </script>

  </head>

  

  <body>
    
    

    

    <div class="subnavd" id="main">
      <div id="header" class="pageheaded">
        <div class="site">
          <div class="logo">
            <a href="http://github.com"><img src="/images/modules/header/logov3.png" alt="github" /></a>
          </div>
          
          <div class="topsearch">
  
    <form action="/search" id="top_search_form" method="get">
      <a href="/search" class="advanced-search tooltipped downwards" title="Advanced Search">Advanced Search</a>
      <input type="search" class="search my_repos_autocompleter" name="q" results="5" placeholder="Search&hellip;" /> <input type="submit" value="Search" class="button" />
      <input type="hidden" name="type" value="Everything" />
      <input type="hidden" name="repo" value="" />
      <input type="hidden" name="langOverride" value="" />
      <input type="hidden" name="start_value" value="1" />
    </form>
  
  
    <ul class="nav logged_out">
      
        <li><a href="http://github.com">Home</a></li>
        <li class="pricing"><a href="/plans">Pricing and Signup</a></li>
        <li><a href="http://github.com/explore">Explore GitHub</a></li>
        
        <li><a href="/blog">Blog</a></li>
      
      <li><a href="https://github.com/login">Login</a></li>
    </ul>
  
</div>

        </div>
      </div>

      
      
        
    <div class="site">
      <div class="pagehead repohead vis-public   ">
        <h1>
          <a href="/cchandler">cchandler</a> / <strong><a href="http://github.com/cchandler/codekata19-mapreduce">codekata19-mapreduce</a></strong>
          
          
        </h1>

        
    <ul class="actions">
      

      
        <li class="for-owner" style="display:none"><a href="https://github.com/cchandler/codekata19-mapreduce/edit" class="minibutton btn-admin "><span><span class="icon"></span>Admin</span></a></li>
        <li>
          <a href="/cchandler/codekata19-mapreduce/toggle_watch" class="minibutton btn-watch " id="watch_button" style="display:none"><span><span class="icon"></span>Watch</span></a>
          <a href="/cchandler/codekata19-mapreduce/toggle_watch" class="minibutton btn-watch " id="unwatch_button" style="display:none"><span><span class="icon"></span>Unwatch</span></a>
        </li>
        
          
            <li class="for-notforked" style="display:none"><a href="/cchandler/codekata19-mapreduce/fork" class="minibutton btn-fork " id="fork_button" onclick="var f = document.createElement('form'); f.style.display = 'none'; this.parentNode.appendChild(f); f.method = 'POST'; f.action = this.href;var s = document.createElement('input'); s.setAttribute('type', 'hidden'); s.setAttribute('name', 'authenticity_token'); s.setAttribute('value', 'ea87557bcf55c8532e524e9ec7852808609d51a0'); f.appendChild(s);f.submit();return false;"><span><span class="icon"></span>Fork</span></a></li>
            <li class="for-hasfork" style="display:none"><a href="#" class="minibutton btn-fork " id="your_fork_button"><span><span class="icon"></span>Your Fork</span></a></li>
          
          <li id="pull_request_item" style="display:none"><a href="/cchandler/codekata19-mapreduce/pull_request/" class="minibutton btn-pull-request "><span><span class="icon"></span>Pull Request</span></a></li>
          <li><a href="#" class="minibutton btn-download " id="download_button"><span><span class="icon"></span>Download Source</span></a></li>
        
      
      
      <li class="repostats">
        <ul class="repo-stats">
          <li class="watchers"><a href="/cchandler/codekata19-mapreduce/watchers" title="Watchers" class="tooltipped downwards">1</a></li>
          <li class="forks"><a href="/cchandler/codekata19-mapreduce/network" title="Forks" class="tooltipped downwards">1</a></li>
        </ul>
      </li>
    </ul>


        
  <ul class="tabs">
    <li><a href="http://github.com/cchandler/codekata19-mapreduce/tree/master" class="selected" highlight="repo_source">Source</a></li>
    <li><a href="http://github.com/cchandler/codekata19-mapreduce/commits/master" highlight="repo_commits">Commits</a></li>

    
    <li><a href="/cchandler/codekata19-mapreduce/network" highlight="repo_network">Network (1)</a></li>

    

    
      
      <li><a href="/cchandler/codekata19-mapreduce/issues" highlight="issues">Issues (0)</a></li>
    

    
      
      <li><a href="/cchandler/codekata19-mapreduce/downloads">Downloads (0)</a></li>
    

    
      
      <li><a href="http://wiki.github.com/cchandler/codekata19-mapreduce/">Wiki (1)</a></li>
    

    <li><a href="/cchandler/codekata19-mapreduce/graphs" highlight="repo_graphs">Graphs</a></li>

    <li class="contextswitch nochoices">
      <span class="toggle leftwards" >
        <em>Branch:</em>
        <code>master</code>
      </span>
    </li>
  </ul>

  <div style="display:none" id="pl-description"><p><em class="placeholder">click here to add a description</em></p></div>
  <div style="display:none" id="pl-homepage"><p><em class="placeholder">click here to add a homepage</em></p></div>

  <div class="subnav-bar">
  
  <ul>
    <li>
      <a href="#" class="dropdown">Switch Branches (1)</a>
      <ul>
        
          
            <li><strong>master &#x2713;</strong></li>
            
      </ul>
    </li>
    <li>
      <a href="#" class="dropdown defunct">Switch Tags (0)</a>
      
    </li>
    <li>
    
    <a href="/cchandler/codekata19-mapreduce/branches" class="manage">Branch List</a>
    
    </li>
  </ul>
</div>

  
  
  
  
  


        
    <div id="repo_details" class="metabox clearfix">
      <div id="repo_details_loader" class="metabox-loader" style="display:none">Sending Request&hellip;</div>

      

      <div id="repository_description" rel="repository_description_edit">
        
          <p>MapReduce implementation of Code Kata 19
            <span id="read_more" style="display:none">&mdash; <a href="#readme">Read more</a></span>
          </p>
        
      </div>
      <div id="repository_description_edit" style="display:none;" class="inline-edit">
        <form action="/cchandler/codekata19-mapreduce/edit/update" method="post"><div style="margin:0;padding:0"><input name="authenticity_token" type="hidden" value="ea87557bcf55c8532e524e9ec7852808609d51a0" /></div>
          <input type="hidden" name="field" value="repository_description">
          <input type="text" class="textfield" name="value" value="MapReduce implementation of Code Kata 19">
          <div class="form-actions">
            <button class="minibutton"><span>Save</span></button> &nbsp; <a href="#" class="cancel">Cancel</a>
          </div>
        </form>
      </div>

      
      <div class="repository-homepage" id="repository_homepage" rel="repository_homepage_edit">
        <p><a href="http://github.com/cchandler/codekata19-mapreduce" rel="nofollow">http://github.com/cchandler/codekata19-mapreduce</a></p>
      </div>
      <div id="repository_homepage_edit" style="display:none;" class="inline-edit">
        <form action="/cchandler/codekata19-mapreduce/edit/update" method="post"><div style="margin:0;padding:0"><input name="authenticity_token" type="hidden" value="ea87557bcf55c8532e524e9ec7852808609d51a0" /></div>
          <input type="hidden" name="field" value="repository_homepage">
          <input type="text" class="textfield" name="value" value="http://github.com/cchandler/codekata19-mapreduce">
          <div class="form-actions">
            <button class="minibutton"><span>Save</span></button> &nbsp; <a href="#" class="cancel">Cancel</a>
          </div>
        </form>
      </div>

      <div class="rule "></div>

      <div id="url_box" class="url-box">
        <ul class="clone-urls">
          
            
            <li id="http_clone_url"><a href="http://github.com/cchandler/codekata19-mapreduce.git" data-permissions="Read-Only">HTTP</a></li>
            <li id="public_clone_url"><a href="git://github.com/cchandler/codekata19-mapreduce.git" data-permissions="Read-Only">Git Read-Only</a></li>
          
        </ul>
        <input type="text" spellcheck="false" id="url_field" class="url-field" />
              <span style="display:none" id="url_box_clippy"></span>
      <span id="clippy_tooltip_url_box_clippy" class="clippy-tooltip tooltipped" title="copy to clipboard">
      <object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000"
              width="14"
              height="14"
              class="clippy"
              id="clippy" >
      <param name="movie" value="http://assets1.github.com/flash/clippy.swf?v5"/>
      <param name="allowScriptAccess" value="always" />
      <param name="quality" value="high" />
      <param name="scale" value="noscale" />
      <param NAME="FlashVars" value="id=url_box_clippy&amp;copied=&amp;copyto=">
      <param name="bgcolor" value="#FFFFFF">
      <param name="wmode" value="opaque">
      <embed src="http://assets1.github.com/flash/clippy.swf?v5"
             width="14"
             height="14"
             name="clippy"
             quality="high"
             allowScriptAccess="always"
             type="application/x-shockwave-flash"
             pluginspage="http://www.macromedia.com/go/getflashplayer"
             FlashVars="id=url_box_clippy&amp;copied=&amp;copyto="
             bgcolor="#FFFFFF"
             wmode="opaque"
      />
      </object>
      </span>

        <p id="url_description">This URL has <strong>Read+Write</strong> access</p>
      </div>
    </div>


        

      </div><!-- /.pagehead -->

      









<script type="text/javascript">
  GitHub.currentCommitRef = "master"
  GitHub.currentRepoOwner = "cchandler"
  GitHub.currentRepo = "codekata19-mapreduce"
  GitHub.downloadRepo = '/cchandler/codekata19-mapreduce/archives/master'

  

  
</script>










  <div id="commit">
    <div class="group">
        
  <div class="envelope commit">
    <div class="human">
      
        <div class="message"><pre><a href="/cchandler/codekata19-mapreduce/commit/21a837360fac48612eb2d09a4d0da46b29b7f10d">Updated readme with link</a> </pre></div>
      

      <div class="actor">
        <div class="gravatar">
          
          <img src="http://www.gravatar.com/avatar/8538181748abc08fe5118853c363b694?s=30&d=http%3A%2F%2Fgithub.com%2Fimages%2Fgravatars%2Fgravatar-30.png" alt="" width="30" height="30"  />
        </div>
        <div class="name"><a href="/cchandler">cchandler</a> <span>(author)</span></div>
        <div class="date">
          <abbr class="relatize" title="2010-04-19 15:07:45">Mon Apr 19 15:07:45 -0700 2010</abbr>
        </div>
      </div>

      

    </div>
    <div class="machine">
      <span>c</span>ommit&nbsp;&nbsp;<a href="/cchandler/codekata19-mapreduce/commit/21a837360fac48612eb2d09a4d0da46b29b7f10d" hotkey="c">21a837360fac48612eb2</a><br />
      <span>t</span>ree&nbsp;&nbsp;&nbsp;&nbsp;<a href="/cchandler/codekata19-mapreduce/tree/21a837360fac48612eb2d09a4d0da46b29b7f10d/src/main/java/codekata19/CodeKata19Search.java" hotkey="t">14dac1b1155918a4ff41</a><br />
      
        <span>p</span>arent&nbsp;
        
        <a href="/cchandler/codekata19-mapreduce/commit/5934fc48648774fb36b1bb59420819b0083c76ec" hotkey="p">5934fc48648774fb36b1</a>
      

    </div>
  </div>

    </div>
  </div>



  
    <div id="path">
      <b><a href="/cchandler/codekata19-mapreduce/tree/master">codekata19-mapreduce</a></b> / <a href="/cchandler/codekata19-mapreduce/tree/master/src">src</a> / <a href="/cchandler/codekata19-mapreduce/tree/master/src/main">main</a> / <a href="/cchandler/codekata19-mapreduce/tree/master/src/main/java">java</a> / <a href="/cchandler/codekata19-mapreduce/tree/master/src/main/java/codekata19">codekata19</a> / CodeKata19.java       <span style="display:none" id="clippy_3709">src/main/java/codekata19/CodeKata19.java</span>
      
      <object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000"
              width="110"
              height="14"
              class="clippy"
              id="clippy" >
      <param name="movie" value="/flash/clippy.swf?v5"/>
      <param name="allowScriptAccess" value="always" />
      <param name="quality" value="high" />
      <param name="scale" value="noscale" />
      <param NAME="FlashVars" value="id=clippy_3709&amp;copied=copied!&amp;copyto=copy to clipboard">
      <param name="bgcolor" value="#FFFFFF">
      <param name="wmode" value="opaque">
      <embed src="/flash/clippy.swf?v5"
             width="110"
             height="14"
             name="clippy"
             quality="high"
             allowScriptAccess="always"
             type="application/x-shockwave-flash"
             pluginspage="http://www.macromedia.com/go/getflashplayer"
             FlashVars="id=clippy_3709&amp;copied=copied!&amp;copyto=copy to clipboard"
             bgcolor="#FFFFFF"
             wmode="opaque"
      />
      </object>
      

    </div>

    <div id="files">
      <div class="file">
        <div class="meta">
          <div class="info">
            <span class="icon"><img alt="Txt" height="16" src="http://assets1.github.com/images/icons/txt.png?f75d32238eb91554988de35b0f878b097d89187d" width="16" /></span>
            <span class="mode" title="File Mode">100644</span>
            
              <span>168 lines (136 sloc)</span>
            
            <span>4.98 kb</span>
          </div>
          <ul class="actions">
            
              <li><a id="file-edit-link" href="#" rel="/cchandler/codekata19-mapreduce/file-edit/__ref__/src/main/java/codekata19/CodeKata19.java">edit</a></li>
            
            <li><a href="/cchandler/codekata19-mapreduce/raw/master/src/main/java/codekata19/CodeKata19.java" id="raw-url">raw</a></li>
            
              <li><a href="/cchandler/codekata19-mapreduce/blame/master/src/main/java/codekata19/CodeKata19.java">blame</a></li>
            
            <li><a href="/cchandler/codekata19-mapreduce/commits/master/src/main/java/codekata19/CodeKata19.java">history</a></li>
          </ul>
        </div>
        
  <div class="data syntax type-java">
    
      <table cellpadding="0" cellspacing="0">
        <tr>
          <td>
            <pre class="line_numbers"><span id="LID1" rel="#L1">1</span>
<span id="LID2" rel="#L2">2</span>
<span id="LID3" rel="#L3">3</span>
<span id="LID4" rel="#L4">4</span>
<span id="LID5" rel="#L5">5</span>
<span id="LID6" rel="#L6">6</span>
<span id="LID7" rel="#L7">7</span>
<span id="LID8" rel="#L8">8</span>
<span id="LID9" rel="#L9">9</span>
<span id="LID10" rel="#L10">10</span>
<span id="LID11" rel="#L11">11</span>
<span id="LID12" rel="#L12">12</span>
<span id="LID13" rel="#L13">13</span>
<span id="LID14" rel="#L14">14</span>
<span id="LID15" rel="#L15">15</span>
<span id="LID16" rel="#L16">16</span>
<span id="LID17" rel="#L17">17</span>
<span id="LID18" rel="#L18">18</span>
<span id="LID19" rel="#L19">19</span>
<span id="LID20" rel="#L20">20</span>
<span id="LID21" rel="#L21">21</span>
<span id="LID22" rel="#L22">22</span>
<span id="LID23" rel="#L23">23</span>
<span id="LID24" rel="#L24">24</span>
<span id="LID25" rel="#L25">25</span>
<span id="LID26" rel="#L26">26</span>
<span id="LID27" rel="#L27">27</span>
<span id="LID28" rel="#L28">28</span>
<span id="LID29" rel="#L29">29</span>
<span id="LID30" rel="#L30">30</span>
<span id="LID31" rel="#L31">31</span>
<span id="LID32" rel="#L32">32</span>
<span id="LID33" rel="#L33">33</span>
<span id="LID34" rel="#L34">34</span>
<span id="LID35" rel="#L35">35</span>
<span id="LID36" rel="#L36">36</span>
<span id="LID37" rel="#L37">37</span>
<span id="LID38" rel="#L38">38</span>
<span id="LID39" rel="#L39">39</span>
<span id="LID40" rel="#L40">40</span>
<span id="LID41" rel="#L41">41</span>
<span id="LID42" rel="#L42">42</span>
<span id="LID43" rel="#L43">43</span>
<span id="LID44" rel="#L44">44</span>
<span id="LID45" rel="#L45">45</span>
<span id="LID46" rel="#L46">46</span>
<span id="LID47" rel="#L47">47</span>
<span id="LID48" rel="#L48">48</span>
<span id="LID49" rel="#L49">49</span>
<span id="LID50" rel="#L50">50</span>
<span id="LID51" rel="#L51">51</span>
<span id="LID52" rel="#L52">52</span>
<span id="LID53" rel="#L53">53</span>
<span id="LID54" rel="#L54">54</span>
<span id="LID55" rel="#L55">55</span>
<span id="LID56" rel="#L56">56</span>
<span id="LID57" rel="#L57">57</span>
<span id="LID58" rel="#L58">58</span>
<span id="LID59" rel="#L59">59</span>
<span id="LID60" rel="#L60">60</span>
<span id="LID61" rel="#L61">61</span>
<span id="LID62" rel="#L62">62</span>
<span id="LID63" rel="#L63">63</span>
<span id="LID64" rel="#L64">64</span>
<span id="LID65" rel="#L65">65</span>
<span id="LID66" rel="#L66">66</span>
<span id="LID67" rel="#L67">67</span>
<span id="LID68" rel="#L68">68</span>
<span id="LID69" rel="#L69">69</span>
<span id="LID70" rel="#L70">70</span>
<span id="LID71" rel="#L71">71</span>
<span id="LID72" rel="#L72">72</span>
<span id="LID73" rel="#L73">73</span>
<span id="LID74" rel="#L74">74</span>
<span id="LID75" rel="#L75">75</span>
<span id="LID76" rel="#L76">76</span>
<span id="LID77" rel="#L77">77</span>
<span id="LID78" rel="#L78">78</span>
<span id="LID79" rel="#L79">79</span>
<span id="LID80" rel="#L80">80</span>
<span id="LID81" rel="#L81">81</span>
<span id="LID82" rel="#L82">82</span>
<span id="LID83" rel="#L83">83</span>
<span id="LID84" rel="#L84">84</span>
<span id="LID85" rel="#L85">85</span>
<span id="LID86" rel="#L86">86</span>
<span id="LID87" rel="#L87">87</span>
<span id="LID88" rel="#L88">88</span>
<span id="LID89" rel="#L89">89</span>
<span id="LID90" rel="#L90">90</span>
<span id="LID91" rel="#L91">91</span>
<span id="LID92" rel="#L92">92</span>
<span id="LID93" rel="#L93">93</span>
<span id="LID94" rel="#L94">94</span>
<span id="LID95" rel="#L95">95</span>
<span id="LID96" rel="#L96">96</span>
<span id="LID97" rel="#L97">97</span>
<span id="LID98" rel="#L98">98</span>
<span id="LID99" rel="#L99">99</span>
<span id="LID100" rel="#L100">100</span>
<span id="LID101" rel="#L101">101</span>
<span id="LID102" rel="#L102">102</span>
<span id="LID103" rel="#L103">103</span>
<span id="LID104" rel="#L104">104</span>
<span id="LID105" rel="#L105">105</span>
<span id="LID106" rel="#L106">106</span>
<span id="LID107" rel="#L107">107</span>
<span id="LID108" rel="#L108">108</span>
<span id="LID109" rel="#L109">109</span>
<span id="LID110" rel="#L110">110</span>
<span id="LID111" rel="#L111">111</span>
<span id="LID112" rel="#L112">112</span>
<span id="LID113" rel="#L113">113</span>
<span id="LID114" rel="#L114">114</span>
<span id="LID115" rel="#L115">115</span>
<span id="LID116" rel="#L116">116</span>
<span id="LID117" rel="#L117">117</span>
<span id="LID118" rel="#L118">118</span>
<span id="LID119" rel="#L119">119</span>
<span id="LID120" rel="#L120">120</span>
<span id="LID121" rel="#L121">121</span>
<span id="LID122" rel="#L122">122</span>
<span id="LID123" rel="#L123">123</span>
<span id="LID124" rel="#L124">124</span>
<span id="LID125" rel="#L125">125</span>
<span id="LID126" rel="#L126">126</span>
<span id="LID127" rel="#L127">127</span>
<span id="LID128" rel="#L128">128</span>
<span id="LID129" rel="#L129">129</span>
<span id="LID130" rel="#L130">130</span>
<span id="LID131" rel="#L131">131</span>
<span id="LID132" rel="#L132">132</span>
<span id="LID133" rel="#L133">133</span>
<span id="LID134" rel="#L134">134</span>
<span id="LID135" rel="#L135">135</span>
<span id="LID136" rel="#L136">136</span>
<span id="LID137" rel="#L137">137</span>
<span id="LID138" rel="#L138">138</span>
<span id="LID139" rel="#L139">139</span>
<span id="LID140" rel="#L140">140</span>
<span id="LID141" rel="#L141">141</span>
<span id="LID142" rel="#L142">142</span>
<span id="LID143" rel="#L143">143</span>
<span id="LID144" rel="#L144">144</span>
<span id="LID145" rel="#L145">145</span>
<span id="LID146" rel="#L146">146</span>
<span id="LID147" rel="#L147">147</span>
<span id="LID148" rel="#L148">148</span>
<span id="LID149" rel="#L149">149</span>
<span id="LID150" rel="#L150">150</span>
<span id="LID151" rel="#L151">151</span>
<span id="LID152" rel="#L152">152</span>
<span id="LID153" rel="#L153">153</span>
<span id="LID154" rel="#L154">154</span>
<span id="LID155" rel="#L155">155</span>
<span id="LID156" rel="#L156">156</span>
<span id="LID157" rel="#L157">157</span>
<span id="LID158" rel="#L158">158</span>
<span id="LID159" rel="#L159">159</span>
<span id="LID160" rel="#L160">160</span>
<span id="LID161" rel="#L161">161</span>
<span id="LID162" rel="#L162">162</span>
<span id="LID163" rel="#L163">163</span>
<span id="LID164" rel="#L164">164</span>
<span id="LID165" rel="#L165">165</span>
<span id="LID166" rel="#L166">166</span>
<span id="LID167" rel="#L167">167</span>
<span id="LID168" rel="#L168">168</span>
</pre>
          </td>
          <td width="100%">
            
              <div class="highlight"><pre><div class='line' id='LC1'><span class="kn">package</span> <span class="n">codekata19</span><span class="o">;</span></div><div class='line' id='LC2'><br/></div><div class='line' id='LC3'><span class="kn">import</span> <span class="nn">java.io.*</span><span class="o">;</span></div><div class='line' id='LC4'><span class="kn">import</span> <span class="nn">java.util.*</span><span class="o">;</span></div><div class='line' id='LC5'><br/></div><div class='line' id='LC6'><span class="kn">import</span> <span class="nn">org.apache.hadoop.fs.Path</span><span class="o">;</span></div><div class='line' id='LC7'><span class="kn">import</span> <span class="nn">org.apache.hadoop.filecache.DistributedCache</span><span class="o">;</span></div><div class='line' id='LC8'><span class="kn">import</span> <span class="nn">org.apache.hadoop.conf.*</span><span class="o">;</span></div><div class='line' id='LC9'><span class="kn">import</span> <span class="nn">org.apache.hadoop.io.*</span><span class="o">;</span></div><div class='line' id='LC10'><span class="kn">import</span> <span class="nn">org.apache.hadoop.mapred.*</span><span class="o">;</span></div><div class='line' id='LC11'><span class="kn">import</span> <span class="nn">org.apache.hadoop.util.*</span><span class="o">;</span></div><div class='line' id='LC12'><br/></div><div class='line' id='LC13'><span class="kd">public</span> <span class="kd">class</span> <span class="nc">CodeKata19</span> <span class="kd">extends</span> <span class="n">Configured</span> <span class="kd">implements</span> <span class="n">Tool</span></div><div class='line' id='LC14'><span class="o">{</span></div><div class='line' id='LC15'>&nbsp;&nbsp;<span class="kd">public</span> <span class="kd">static</span> <span class="kd">class</span> <span class="nc">Map</span> <span class="kd">extends</span> <span class="n">MapReduceBase</span> <span class="kd">implements</span> <span class="n">Mapper</span><span class="o">&lt;</span><span class="n">LongWritable</span><span class="o">,</span> <span class="n">Text</span><span class="o">,</span> <span class="n">Text</span><span class="o">,</span> <span class="n">Text</span><span class="o">&gt;</span> <span class="o">{</span></div><div class='line' id='LC16'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="kd">private</span> <span class="kd">final</span> <span class="kd">static</span> <span class="n">IntWritable</span> <span class="n">one</span> <span class="o">=</span> <span class="k">new</span> <span class="n">IntWritable</span><span class="o">(</span><span class="mi">1</span><span class="o">);</span></div><div class='line' id='LC17'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="kd">private</span> <span class="n">Text</span> <span class="n">word</span> <span class="o">=</span> <span class="k">new</span> <span class="n">Text</span><span class="o">();</span></div><div class='line' id='LC18'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="kd">private</span> <span class="n">Text</span> <span class="n">currentPermutation</span> <span class="o">=</span> <span class="k">new</span> <span class="n">Text</span><span class="o">();</span></div><div class='line' id='LC19'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="kd">public</span> <span class="kd">static</span> <span class="n">java</span><span class="o">.</span><span class="na">util</span><span class="o">.</span><span class="na">Map</span><span class="o">&lt;</span><span class="n">String</span><span class="o">,</span><span class="n">String</span><span class="o">&gt;</span> <span class="n">dictionary</span> <span class="o">=</span> <span class="k">new</span> <span class="n">HashMap</span><span class="o">&lt;</span><span class="n">String</span><span class="o">,</span><span class="n">String</span><span class="o">&gt;();</span></div><div class='line' id='LC20'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="kd">public</span> <span class="kd">final</span> <span class="kd">static</span> <span class="kt">char</span><span class="o">[]</span> <span class="n">letters</span> <span class="o">=</span> <span class="o">{</span><span class="sc">&#39;a&#39;</span><span class="o">,</span><span class="sc">&#39;b&#39;</span><span class="o">,</span><span class="sc">&#39;c&#39;</span><span class="o">,</span><span class="sc">&#39;d&#39;</span><span class="o">,</span><span class="sc">&#39;e&#39;</span><span class="o">,</span><span class="sc">&#39;f&#39;</span><span class="o">,</span><span class="sc">&#39;g&#39;</span><span class="o">,</span><span class="sc">&#39;h&#39;</span><span class="o">,</span><span class="sc">&#39;i&#39;</span><span class="o">,</span> <span class="sc">&#39;j&#39;</span><span class="o">,</span><span class="sc">&#39;k&#39;</span><span class="o">,</span><span class="sc">&#39;l&#39;</span><span class="o">,</span><span class="sc">&#39;m&#39;</span><span class="o">,</span><span class="sc">&#39;n&#39;</span><span class="o">,</span><span class="sc">&#39;o&#39;</span><span class="o">,</span><span class="sc">&#39;p&#39;</span><span class="o">,</span><span class="sc">&#39;q&#39;</span><span class="o">,</span><span class="sc">&#39;r&#39;</span><span class="o">,</span><span class="sc">&#39;s&#39;</span><span class="o">,</span><span class="sc">&#39;t&#39;</span><span class="o">,</span><span class="sc">&#39;u&#39;</span><span class="o">,</span><span class="sc">&#39;v&#39;</span><span class="o">,</span><span class="sc">&#39;w&#39;</span><span class="o">,</span><span class="sc">&#39;x&#39;</span><span class="o">,</span><span class="sc">&#39;y&#39;</span><span class="o">,</span><span class="sc">&#39;z&#39;</span><span class="o">};</span></div><div class='line' id='LC21'>&nbsp;&nbsp;&nbsp;&nbsp;</div><div class='line' id='LC22'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="kd">public</span> <span class="kt">void</span> <span class="nf">configure</span><span class="o">(</span><span class="n">JobConf</span> <span class="n">job</span><span class="o">)</span> <span class="o">{</span></div><div class='line' id='LC23'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="n">String</span> <span class="n">wordlist</span> <span class="o">=</span> <span class="n">job</span><span class="o">.</span><span class="na">get</span><span class="o">(</span><span class="s">&quot;wordlist&quot;</span><span class="o">);</span></div><div class='line' id='LC24'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="n">CodeKata19</span><span class="o">.</span><span class="na">loadDictionary</span><span class="o">(</span><span class="n">wordlist</span><span class="o">);</span></div><div class='line' id='LC25'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="o">}</span></div><div class='line' id='LC26'>&nbsp;&nbsp;&nbsp;&nbsp;</div><div class='line' id='LC27'>&nbsp;&nbsp;</div><div class='line' id='LC28'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="kd">public</span> <span class="kt">void</span> <span class="nf">map</span><span class="o">(</span><span class="n">LongWritable</span> <span class="n">key</span><span class="o">,</span> <span class="n">Text</span> <span class="n">value</span><span class="o">,</span> <span class="n">OutputCollector</span><span class="o">&lt;</span><span class="n">Text</span><span class="o">,</span> <span class="n">Text</span><span class="o">&gt;</span> <span class="n">output</span><span class="o">,</span> <span class="n">Reporter</span> <span class="n">reporter</span><span class="o">)</span> <span class="kd">throws</span> <span class="n">IOException</span> <span class="o">{</span></div><div class='line' id='LC29'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div><div class='line' id='LC30'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="n">String</span> <span class="n">comp</span> <span class="o">=</span> <span class="n">value</span><span class="o">.</span><span class="na">toString</span><span class="o">().</span><span class="na">trim</span><span class="o">();</span></div><div class='line' id='LC31'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="kt">char</span><span class="o">[]</span> <span class="n">array</span> <span class="o">=</span> <span class="n">value</span><span class="o">.</span><span class="na">toString</span><span class="o">().</span><span class="na">toCharArray</span><span class="o">();</span></div><div class='line' id='LC32'><br/></div><div class='line' id='LC33'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="k">for</span><span class="o">(</span><span class="kt">int</span> <span class="n">i</span> <span class="o">=</span> <span class="mi">0</span><span class="o">;</span> <span class="n">i</span> <span class="o">&lt;</span> <span class="n">array</span><span class="o">.</span><span class="na">length</span><span class="o">;</span> <span class="n">i</span><span class="o">++)</span></div><div class='line' id='LC34'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="o">{</span></div><div class='line' id='LC35'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="k">for</span><span class="o">(</span><span class="kt">char</span> <span class="n">c</span> <span class="o">:</span> <span class="n">CodeKata19</span><span class="o">.</span><span class="na">Map</span><span class="o">.</span><span class="na">letters</span><span class="o">)</span></div><div class='line' id='LC36'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="o">{</span></div><div class='line' id='LC37'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="kt">char</span><span class="o">[]</span> <span class="n">newString</span> <span class="o">=</span> <span class="k">new</span> <span class="kt">char</span><span class="o">[</span><span class="n">array</span><span class="o">.</span><span class="na">length</span><span class="o">];</span></div><div class='line' id='LC38'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="n">System</span><span class="o">.</span><span class="na">arraycopy</span><span class="o">(</span> <span class="n">array</span><span class="o">,</span> <span class="mi">0</span><span class="o">,</span> <span class="n">newString</span><span class="o">,</span> <span class="mi">0</span><span class="o">,</span> <span class="n">array</span><span class="o">.</span><span class="na">length</span> <span class="o">);</span></div><div class='line' id='LC39'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="n">newString</span><span class="o">[</span><span class="n">i</span><span class="o">]</span> <span class="o">=</span> <span class="n">c</span><span class="o">;</span></div><div class='line' id='LC40'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="n">String</span> <span class="n">newString1</span> <span class="o">=</span> <span class="n">String</span><span class="o">.</span><span class="na">valueOf</span><span class="o">(</span><span class="n">newString</span><span class="o">);</span></div><div class='line' id='LC41'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="k">if</span><span class="o">(</span><span class="n">CodeKata19</span><span class="o">.</span><span class="na">Map</span><span class="o">.</span><span class="na">dictionary</span><span class="o">.</span><span class="na">containsKey</span><span class="o">(</span> <span class="n">newString1</span> <span class="o">)</span> <span class="o">&amp;&amp;</span> <span class="o">!</span><span class="n">newString1</span><span class="o">.</span><span class="na">equalsIgnoreCase</span><span class="o">(</span><span class="n">comp</span><span class="o">))</span></div><div class='line' id='LC42'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="o">{</span></div><div class='line' id='LC43'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="n">currentPermutation</span><span class="o">.</span><span class="na">set</span><span class="o">(</span><span class="n">newString1</span><span class="o">);</span></div><div class='line' id='LC44'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="n">output</span><span class="o">.</span><span class="na">collect</span><span class="o">(</span><span class="n">value</span><span class="o">,</span> <span class="n">currentPermutation</span><span class="o">);</span></div><div class='line' id='LC45'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="o">}</span></div><div class='line' id='LC46'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="o">}</span></div><div class='line' id='LC47'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="o">}</span></div><div class='line' id='LC48'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div><div class='line' id='LC49'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="o">}</span></div><div class='line' id='LC50'>&nbsp;&nbsp;<span class="o">}</span></div><div class='line' id='LC51'>&nbsp;&nbsp;</div><div class='line' id='LC52'>&nbsp;&nbsp;<span class="kd">public</span> <span class="kd">static</span> <span class="kd">class</span> <span class="nc">Reduce</span> <span class="kd">extends</span> <span class="n">MapReduceBase</span> <span class="kd">implements</span> <span class="n">Reducer</span><span class="o">&lt;</span><span class="n">Text</span><span class="o">,</span> <span class="n">Text</span><span class="o">,</span> <span class="n">Text</span><span class="o">,</span> <span class="n">Text</span><span class="o">&gt;</span> <span class="o">{</span></div><div class='line' id='LC53'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="kd">public</span> <span class="kt">void</span> <span class="nf">reduce</span><span class="o">(</span><span class="n">Text</span> <span class="n">key</span><span class="o">,</span> <span class="n">Iterator</span><span class="o">&lt;</span><span class="n">Text</span><span class="o">&gt;</span> <span class="n">values</span><span class="o">,</span> <span class="n">OutputCollector</span><span class="o">&lt;</span><span class="n">Text</span><span class="o">,</span> <span class="n">Text</span><span class="o">&gt;</span> <span class="n">output</span><span class="o">,</span> <span class="n">Reporter</span> <span class="n">reporter</span><span class="o">)</span> <span class="kd">throws</span> <span class="n">IOException</span> <span class="o">{</span></div><div class='line' id='LC54'>&nbsp;&nbsp;&nbsp;&nbsp;</div><div class='line' id='LC55'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="n">java</span><span class="o">.</span><span class="na">util</span><span class="o">.</span><span class="na">Map</span><span class="o">&lt;</span><span class="n">String</span><span class="o">,</span><span class="n">String</span><span class="o">&gt;</span> <span class="n">alreadySeen</span> <span class="o">=</span> <span class="k">new</span> <span class="n">HashMap</span><span class="o">&lt;</span><span class="n">String</span><span class="o">,</span><span class="n">String</span><span class="o">&gt;();</span></div><div class='line' id='LC56'>&nbsp;&nbsp;&nbsp;&nbsp;</div><div class='line' id='LC57'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="k">while</span><span class="o">(</span><span class="n">values</span><span class="o">.</span><span class="na">hasNext</span><span class="o">())</span></div><div class='line' id='LC58'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="o">{</span></div><div class='line' id='LC59'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="n">String</span> <span class="n">s</span> <span class="o">=</span> <span class="n">values</span><span class="o">.</span><span class="na">next</span><span class="o">().</span><span class="na">toString</span><span class="o">();</span></div><div class='line' id='LC60'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="k">if</span><span class="o">(!</span><span class="n">alreadySeen</span><span class="o">.</span><span class="na">containsKey</span><span class="o">(</span><span class="n">s</span><span class="o">))</span></div><div class='line' id='LC61'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="o">{</span></div><div class='line' id='LC62'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="n">alreadySeen</span><span class="o">.</span><span class="na">put</span><span class="o">(</span><span class="n">s</span><span class="o">,</span><span class="s">&quot;&quot;</span><span class="o">);</span></div><div class='line' id='LC63'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="o">}</span></div><div class='line' id='LC64'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="o">}</span></div><div class='line' id='LC65'>&nbsp;&nbsp;&nbsp;&nbsp;</div><div class='line' id='LC66'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="n">StringBuffer</span> <span class="n">buffer</span> <span class="o">=</span> <span class="k">new</span> <span class="n">StringBuffer</span><span class="o">();</span></div><div class='line' id='LC67'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="k">for</span><span class="o">(</span><span class="n">String</span> <span class="n">s</span> <span class="o">:</span> <span class="n">alreadySeen</span><span class="o">.</span><span class="na">keySet</span><span class="o">())</span></div><div class='line' id='LC68'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="o">{</span></div><div class='line' id='LC69'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="n">buffer</span><span class="o">.</span><span class="na">append</span><span class="o">(</span><span class="n">s</span><span class="o">);</span></div><div class='line' id='LC70'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="n">buffer</span><span class="o">.</span><span class="na">append</span><span class="o">(</span><span class="s">&quot;,&quot;</span><span class="o">);</span></div><div class='line' id='LC71'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="o">}</span></div><div class='line' id='LC72'>&nbsp;&nbsp;&nbsp;&nbsp;</div><div class='line' id='LC73'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="n">buffer</span><span class="o">.</span><span class="na">append</span><span class="o">(</span><span class="s">&quot;|-1|WHITE| &quot;</span><span class="o">);</span></div><div class='line' id='LC74'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div><div class='line' id='LC75'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="n">Text</span> <span class="n">combined_output</span> <span class="o">=</span> <span class="k">new</span> <span class="n">Text</span><span class="o">();</span></div><div class='line' id='LC76'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="n">combined_output</span><span class="o">.</span><span class="na">set</span><span class="o">(</span><span class="n">buffer</span><span class="o">.</span><span class="na">toString</span><span class="o">());</span></div><div class='line' id='LC77'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="n">output</span><span class="o">.</span><span class="na">collect</span><span class="o">(</span><span class="n">key</span><span class="o">,</span> <span class="n">combined_output</span><span class="o">);</span></div><div class='line' id='LC78'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div><div class='line' id='LC79'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="o">}</span></div><div class='line' id='LC80'>&nbsp;&nbsp;<span class="o">}</span></div><div class='line' id='LC81'>&nbsp;&nbsp;</div><div class='line' id='LC82'>&nbsp;&nbsp;<span class="kd">public</span> <span class="n">List</span><span class="o">&lt;</span><span class="n">String</span><span class="o">&gt;</span> <span class="n">buildPermutations</span><span class="o">(</span><span class="n">String</span> <span class="n">s</span><span class="o">)</span></div><div class='line' id='LC83'>&nbsp;&nbsp;<span class="o">{</span></div><div class='line' id='LC84'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="kt">char</span><span class="o">[]</span> <span class="n">array</span> <span class="o">=</span> <span class="n">s</span><span class="o">.</span><span class="na">toCharArray</span><span class="o">();</span></div><div class='line' id='LC85'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="n">List</span><span class="o">&lt;</span><span class="n">String</span><span class="o">&gt;</span> <span class="n">results</span> <span class="o">=</span> <span class="k">new</span> <span class="n">LinkedList</span><span class="o">&lt;</span><span class="n">String</span><span class="o">&gt;();</span></div><div class='line' id='LC86'>&nbsp;&nbsp;&nbsp;&nbsp;</div><div class='line' id='LC87'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="k">for</span><span class="o">(</span><span class="kt">int</span> <span class="n">i</span> <span class="o">=</span> <span class="mi">0</span><span class="o">;</span> <span class="n">i</span> <span class="o">&lt;</span> <span class="n">array</span><span class="o">.</span><span class="na">length</span><span class="o">;</span> <span class="n">i</span><span class="o">++)</span></div><div class='line' id='LC88'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="o">{</span></div><div class='line' id='LC89'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="k">for</span><span class="o">(</span><span class="kt">char</span> <span class="n">c</span> <span class="o">:</span> <span class="n">CodeKata19</span><span class="o">.</span><span class="na">Map</span><span class="o">.</span><span class="na">letters</span><span class="o">)</span></div><div class='line' id='LC90'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="o">{</span></div><div class='line' id='LC91'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="kt">char</span><span class="o">[]</span> <span class="n">newString</span> <span class="o">=</span> <span class="k">new</span> <span class="kt">char</span><span class="o">[</span><span class="n">array</span><span class="o">.</span><span class="na">length</span><span class="o">];</span></div><div class='line' id='LC92'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="n">System</span><span class="o">.</span><span class="na">arraycopy</span><span class="o">(</span> <span class="n">array</span><span class="o">,</span> <span class="mi">0</span><span class="o">,</span> <span class="n">newString</span><span class="o">,</span> <span class="mi">0</span><span class="o">,</span> <span class="n">array</span><span class="o">.</span><span class="na">length</span> <span class="o">);</span></div><div class='line' id='LC93'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="n">newString</span><span class="o">[</span><span class="n">i</span><span class="o">]</span> <span class="o">=</span> <span class="n">c</span><span class="o">;</span></div><div class='line' id='LC94'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="n">String</span> <span class="n">newString1</span> <span class="o">=</span> <span class="n">String</span><span class="o">.</span><span class="na">valueOf</span><span class="o">(</span><span class="n">newString</span><span class="o">);</span></div><div class='line' id='LC95'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="k">if</span><span class="o">(</span><span class="n">CodeKata19</span><span class="o">.</span><span class="na">Map</span><span class="o">.</span><span class="na">dictionary</span><span class="o">.</span><span class="na">containsKey</span><span class="o">(</span> <span class="n">newString1</span> <span class="o">)</span> <span class="o">&amp;&amp;</span> <span class="o">!</span><span class="n">newString1</span><span class="o">.</span><span class="na">equals</span><span class="o">(</span><span class="n">s</span><span class="o">))</span></div><div class='line' id='LC96'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="o">{</span></div><div class='line' id='LC97'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="n">results</span><span class="o">.</span><span class="na">add</span><span class="o">(</span><span class="n">newString1</span><span class="o">);</span></div><div class='line' id='LC98'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="o">}</span></div><div class='line' id='LC99'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="o">}</span></div><div class='line' id='LC100'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="o">}</span></div><div class='line' id='LC101'>&nbsp;&nbsp;&nbsp;&nbsp;</div><div class='line' id='LC102'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="k">return</span> <span class="n">results</span><span class="o">;</span></div><div class='line' id='LC103'>&nbsp;&nbsp;<span class="o">}</span></div><div class='line' id='LC104'>&nbsp;&nbsp;</div><div class='line' id='LC105'>&nbsp;&nbsp;<span class="kd">public</span> <span class="kd">static</span> <span class="kt">boolean</span> <span class="nf">loadDictionary</span><span class="o">(</span><span class="n">String</span> <span class="n">file</span><span class="o">)</span></div><div class='line' id='LC106'>&nbsp;&nbsp;<span class="o">{</span></div><div class='line' id='LC107'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="n">System</span><span class="o">.</span><span class="na">out</span><span class="o">.</span><span class="na">println</span><span class="o">(</span><span class="n">CodeKata19</span><span class="o">.</span><span class="na">Map</span><span class="o">.</span><span class="na">dictionary</span><span class="o">);</span></div><div class='line' id='LC108'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="kt">boolean</span> <span class="n">result</span> <span class="o">=</span> <span class="kc">false</span><span class="o">;</span></div><div class='line' id='LC109'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="k">try</span></div><div class='line' id='LC110'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="o">{</span></div><div class='line' id='LC111'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="n">FileInputStream</span> <span class="n">fstream</span> <span class="o">=</span> <span class="k">new</span> <span class="n">FileInputStream</span><span class="o">(</span><span class="n">file</span><span class="o">);</span></div><div class='line' id='LC112'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="n">DataInputStream</span> <span class="n">in</span> <span class="o">=</span> <span class="k">new</span> <span class="n">DataInputStream</span><span class="o">(</span><span class="n">fstream</span><span class="o">);</span></div><div class='line' id='LC113'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="n">BufferedReader</span> <span class="n">br</span> <span class="o">=</span> <span class="k">new</span> <span class="n">BufferedReader</span><span class="o">(</span><span class="k">new</span> <span class="n">InputStreamReader</span><span class="o">(</span><span class="n">in</span><span class="o">));</span></div><div class='line' id='LC114'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="n">String</span> <span class="n">strLine</span><span class="o">;</span></div><div class='line' id='LC115'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="k">while</span> <span class="o">((</span><span class="n">strLine</span> <span class="o">=</span> <span class="n">br</span><span class="o">.</span><span class="na">readLine</span><span class="o">())</span> <span class="o">!=</span> <span class="kc">null</span><span class="o">)</span>   <span class="o">{</span></div><div class='line' id='LC116'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="n">CodeKata19</span><span class="o">.</span><span class="na">Map</span><span class="o">.</span><span class="na">dictionary</span><span class="o">.</span><span class="na">put</span><span class="o">(</span><span class="n">strLine</span><span class="o">,</span> <span class="s">&quot;&quot;</span><span class="o">);</span></div><div class='line' id='LC117'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="o">}</span></div><div class='line' id='LC118'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div><div class='line' id='LC119'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="n">in</span><span class="o">.</span><span class="na">close</span><span class="o">();</span></div><div class='line' id='LC120'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="n">result</span> <span class="o">=</span> <span class="kc">true</span><span class="o">;</span></div><div class='line' id='LC121'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="o">}</span></div><div class='line' id='LC122'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="k">catch</span><span class="o">(</span><span class="n">Exception</span> <span class="n">e</span><span class="o">)</span></div><div class='line' id='LC123'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="o">{</span></div><div class='line' id='LC124'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="n">System</span><span class="o">.</span><span class="na">err</span><span class="o">.</span><span class="na">println</span><span class="o">(</span><span class="s">&quot;Error reading in dictionary!&quot;</span><span class="o">);</span></div><div class='line' id='LC125'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="n">System</span><span class="o">.</span><span class="na">err</span><span class="o">.</span><span class="na">println</span><span class="o">(</span><span class="n">e</span><span class="o">);</span></div><div class='line' id='LC126'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="o">}</span></div><div class='line' id='LC127'>&nbsp;&nbsp;&nbsp;&nbsp;</div><div class='line' id='LC128'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="k">return</span> <span class="n">result</span><span class="o">;</span></div><div class='line' id='LC129'>&nbsp;&nbsp;<span class="o">}</span></div><div class='line' id='LC130'><br/></div><div class='line' id='LC131'>	<span class="kd">public</span> <span class="kt">int</span> <span class="nf">run</span><span class="o">(</span><span class="n">String</span><span class="o">[]</span> <span class="n">args</span><span class="o">)</span> <span class="kd">throws</span> <span class="n">Exception</span> <span class="o">{</span></div><div class='line' id='LC132'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="n">JobConf</span> <span class="n">conf</span> <span class="o">=</span> <span class="k">new</span> <span class="n">JobConf</span><span class="o">(</span><span class="n">getConf</span><span class="o">(),</span> <span class="n">CodeKata19</span><span class="o">.</span><span class="na">class</span><span class="o">);</span></div><div class='line' id='LC133'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="n">conf</span><span class="o">.</span><span class="na">setJobName</span><span class="o">(</span><span class="s">&quot;codekata19&quot;</span><span class="o">);</span></div><div class='line' id='LC134'>&nbsp;&nbsp;</div><div class='line' id='LC135'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="n">conf</span><span class="o">.</span><span class="na">setOutputKeyClass</span><span class="o">(</span><span class="n">Text</span><span class="o">.</span><span class="na">class</span><span class="o">);</span></div><div class='line' id='LC136'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="n">conf</span><span class="o">.</span><span class="na">setOutputValueClass</span><span class="o">(</span><span class="n">Text</span><span class="o">.</span><span class="na">class</span><span class="o">);</span></div><div class='line' id='LC137'>&nbsp;&nbsp;</div><div class='line' id='LC138'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="n">conf</span><span class="o">.</span><span class="na">setMapperClass</span><span class="o">(</span><span class="n">Map</span><span class="o">.</span><span class="na">class</span><span class="o">);</span></div><div class='line' id='LC139'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="n">conf</span><span class="o">.</span><span class="na">setReducerClass</span><span class="o">(</span><span class="n">Reduce</span><span class="o">.</span><span class="na">class</span><span class="o">);</span></div><div class='line' id='LC140'>&nbsp;&nbsp;</div><div class='line' id='LC141'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="n">conf</span><span class="o">.</span><span class="na">setInputFormat</span><span class="o">(</span><span class="n">TextInputFormat</span><span class="o">.</span><span class="na">class</span><span class="o">);</span></div><div class='line' id='LC142'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="n">conf</span><span class="o">.</span><span class="na">setOutputFormat</span><span class="o">(</span><span class="n">TextOutputFormat</span><span class="o">.</span><span class="na">class</span><span class="o">);</span></div><div class='line' id='LC143'>&nbsp;&nbsp;</div><div class='line' id='LC144'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="n">DistributedCache</span><span class="o">.</span><span class="na">addCacheFile</span><span class="o">(</span> <span class="k">new</span> <span class="n">Path</span><span class="o">(</span><span class="n">args</span><span class="o">[</span><span class="mi">1</span><span class="o">]).</span><span class="na">toUri</span><span class="o">(),</span> <span class="n">conf</span><span class="o">);</span></div><div class='line' id='LC145'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="n">conf</span><span class="o">.</span><span class="na">set</span><span class="o">(</span><span class="s">&quot;wordlist&quot;</span><span class="o">,</span> <span class="n">args</span><span class="o">[</span><span class="mi">1</span><span class="o">]);</span></div><div class='line' id='LC146'>&nbsp;&nbsp;</div><div class='line' id='LC147'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="n">FileInputFormat</span><span class="o">.</span><span class="na">setInputPaths</span><span class="o">(</span><span class="n">conf</span><span class="o">,</span> <span class="k">new</span> <span class="n">Path</span><span class="o">(</span><span class="n">args</span><span class="o">[</span><span class="mi">1</span><span class="o">]));</span></div><div class='line' id='LC148'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="n">FileOutputFormat</span><span class="o">.</span><span class="na">setOutputPath</span><span class="o">(</span><span class="n">conf</span><span class="o">,</span> <span class="k">new</span> <span class="n">Path</span><span class="o">(</span><span class="n">args</span><span class="o">[</span><span class="mi">2</span><span class="o">]));</span></div><div class='line' id='LC149'>&nbsp;&nbsp;</div><div class='line' id='LC150'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="n">JobClient</span><span class="o">.</span><span class="na">runJob</span><span class="o">(</span><span class="n">conf</span><span class="o">);</span></div><div class='line' id='LC151'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="k">return</span> <span class="mi">0</span><span class="o">;</span></div><div class='line' id='LC152'>&nbsp;&nbsp;<span class="o">}</span></div><div class='line' id='LC153'><br/></div><div class='line' id='LC154'>	<span class="kd">public</span> <span class="kd">static</span> <span class="kt">void</span> <span class="nf">main</span><span class="o">(</span><span class="n">String</span><span class="o">[]</span> <span class="n">args</span><span class="o">)</span> <span class="kd">throws</span> <span class="n">Exception</span> <span class="o">{</span></div><div class='line' id='LC155'>	  <span class="kt">int</span> <span class="n">res</span> <span class="o">=</span> <span class="mi">0</span><span class="o">;</span></div><div class='line' id='LC156'>	  <span class="k">if</span><span class="o">(</span><span class="n">args</span><span class="o">[</span><span class="mi">0</span><span class="o">].</span><span class="na">equals</span><span class="o">(</span><span class="s">&quot;graph&quot;</span><span class="o">))</span></div><div class='line' id='LC157'>	  <span class="o">{</span></div><div class='line' id='LC158'>	    <span class="n">res</span> <span class="o">=</span> <span class="n">ToolRunner</span><span class="o">.</span><span class="na">run</span><span class="o">(</span><span class="k">new</span> <span class="n">Configuration</span><span class="o">(),</span> <span class="k">new</span> <span class="n">CodeKata19</span><span class="o">(),</span> <span class="n">args</span><span class="o">);</span></div><div class='line' id='LC159'>	  <span class="o">}</span></div><div class='line' id='LC160'>	  <span class="k">else</span></div><div class='line' id='LC161'>	  <span class="o">{</span></div><div class='line' id='LC162'>	    <span class="n">res</span> <span class="o">=</span> <span class="n">ToolRunner</span><span class="o">.</span><span class="na">run</span><span class="o">(</span><span class="k">new</span> <span class="n">Configuration</span><span class="o">(),</span> <span class="k">new</span> <span class="n">CodeKata19Search</span><span class="o">(),</span> <span class="n">args</span><span class="o">);</span></div><div class='line' id='LC163'>	  <span class="o">}</span></div><div class='line' id='LC164'>&nbsp;&nbsp;&nbsp;&nbsp;</div><div class='line' id='LC165'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="n">System</span><span class="o">.</span><span class="na">exit</span><span class="o">(</span><span class="n">res</span><span class="o">);</span></div><div class='line' id='LC166'>&nbsp;&nbsp;<span class="o">}</span></div><div class='line' id='LC167'><span class="o">}</span></div><div class='line' id='LC168'><br/></div></pre></div>
            
          </td>
        </tr>
      </table>
    
  </div>


      </div>
    </div>

  


    </div>
  
      

      <div class="push"></div>
    </div>

    <div id="footer">
      <div class="site">
        <div class="info">
          <div class="links">
            <a href="http://github.com/blog"><b>Blog</b></a> |
            <a href="http://support.github.com">Support</a> |
            <a href="http://github.com/training">Training</a> |
            <a href="http://github.com/contact">Contact</a> |
            <a href="http://develop.github.com">API</a> |
            <a href="http://status.github.com">Status</a> |
            <a href="http://twitter.com/github">Twitter</a> |
            <a href="http://help.github.com">Help</a> |
            <a href="http://github.com/security">Security</a>
          </div>
          <div class="company">
            &copy;
            2010
            <span id="_rrt" title="0.05459s from fe2.rs.github.com">GitHub</span> Inc.
            All rights reserved. |
            <a href="/site/terms">Terms of Service</a> |
            <a href="/site/privacy">Privacy Policy</a>
          </div>
        </div>
        <div class="sponsor">
          <div>
            Powered by the <a href="http://www.rackspace.com ">Dedicated
            Servers</a> and<br/> <a href="http://www.rackspacecloud.com">Cloud
            Computing</a> of Rackspace Hosting<span>&reg;</span>
          </div>
          <a href="http://www.rackspace.com">
            <img alt="Dedicated Server" src="http://assets2.github.com/images/modules/footer/rackspace_logo.png?25e168045a8f6c32ad0d68f405d75e0c4b8a76f1" />
          </a>
        </div>
      </div>
    </div>

    <script>window._auth_token = "ea87557bcf55c8532e524e9ec7852808609d51a0"</script>
    
    
  </body>
</html>

