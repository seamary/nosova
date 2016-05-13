jQuery.parseXML("<messages><msg>asd</msg><msg>asd2</msg></messages>")
#document<messages>​<msg>​asd​</msg>​<msg>​asd2​</msg>​</messages>​
var xml = jQuery.parseXML("<messages><msg>asd</msg><msg>asd2</msg></messages>")
undefined
xml
#document
xml.firstChild
<messages>​<msg>​asd​</msg>​<msg>​asd2​</msg>​</messages>​
xml.getElementsByTagName("messages")
[<messages>​<msg>​asd​</msg>​<msg>​asd2​</msg>​</messages>​]
xml.getElementsByTagName("messages")[0]
<messages>​<msg>​asd​</msg>​<msg>​asd2​</msg>​</messages>​
var root = xml.getElementsByTagName("messages")[0]
undefined
root
<messages>​<msg>​asd​</msg>​<msg>​asd2​</msg>​</messages>​
root.childNodes
[<msg>​asd​</msg>​, <msg>​asd2​</msg>​]
xml.childNodes
[<messages>​<msg>​asd​</msg>​<msg>​asd2​</msg>​</messages>​]
xml.children
[<messages>​<msg>​asd​</msg>​<msg>​asd2​</msg>​</messages>​]
root.children
[<msg>​asd​</msg>​, <msg>​asd2​</msg>​]
root.children[0]
<msg>​asd​</msg>​
var fMsg = root.children[0]
undefined
fMsg
<msg>​asd​</msg>​
fMsg.textContent
"asd"
fMsg.attributes
NamedNodeMap {length: 0}
var xml = jQuery.parseXML("<messages><msg date="2016-05-13 11:17:00">asd</msg><msg>asd2</msg></messages>")
VM1112:1 Uncaught SyntaxError: missing ) after argument list(…)InjectedScript._evaluateOn @ VM390:145InjectedScript._evaluateAndWrap @ VM390:137InjectedScript.evaluate @ VM390:118
var xml = jQuery.parseXML("<messages><msg date="2016-05-13 11:17:00">asd</msg><msg>asd2</msg></messages>")
VM1113:1 Uncaught SyntaxError: missing ) after argument list(…)InjectedScript._evaluateOn @ VM390:145InjectedScript._evaluateAndWrap @ VM390:137InjectedScript.evaluate @ VM390:118
var xml = jQuery.parseXML("<messages><msg date=\"2016-05-13 11:17:00\">asd</msg><msg>asd2</msg></messages>")
undefined
xml