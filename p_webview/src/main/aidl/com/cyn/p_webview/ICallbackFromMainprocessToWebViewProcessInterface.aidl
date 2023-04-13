// ICallbackFromMainprocessToWebViewProcessInterface.aidl
package com.cyn.p_webview;

interface ICallbackFromMainprocessToWebViewProcessInterface {
    void onResult(String kotlinToJavescriptCallBackName, String response);
}
