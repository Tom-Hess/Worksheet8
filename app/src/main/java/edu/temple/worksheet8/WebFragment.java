package edu.temple.worksheet8;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class WebFragment extends Fragment {
    private EditText urlInput;
    private Button execute;
    private WebView browser;

    private String url;
    public static WebFragment newInstance(String url) {
        WebFragment fragment = new WebFragment();
        Bundle args = new Bundle();
        args.putString("url", url);
        fragment.setArguments(args);
        return fragment;
    }


    public WebFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_web, container, false);

        urlInput = (EditText) view.findViewById(R.id.url);
        execute = (Button) view.findViewById(R.id.go);
        browser = (WebView) view.findViewById(R.id.browser);
        browser.getSettings().setJavaScriptEnabled(true);
        browser.setWebViewClient(new WebViewController());

        if (getArguments().getString("url", null) != null) {
            url = getArguments().getString("url", null);
            urlInput.setText(url);
        }
        if(url != null) {
            browser.loadUrl(url);
        }

        execute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url = urlInput.getText().toString();
                browser.loadUrl(url);
            }
        });
        return view;
    }
}

class WebViewController extends WebViewClient {

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }
}
