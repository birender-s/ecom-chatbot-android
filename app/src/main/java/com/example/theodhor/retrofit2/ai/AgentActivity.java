package com.example.theodhor.retrofit2.ai;

import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.theodhor.retrofit2.AzureCommunicator;
import com.example.theodhor.retrofit2.model.ActivityModel;
import com.example.theodhor.retrofit2.Events.ApiTokenModel;
import com.example.theodhor.retrofit2.Events.ConvserationModel;
import com.example.theodhor.retrofit2.Events.StartConversationModel;
import com.example.theodhor.retrofit2.R;
import com.example.theodhor.retrofit2.adapter.ChatAdapter;
import com.example.theodhor.retrofit2.interfaces.ProductDataCallback;
import com.example.theodhor.retrofit2.interfaces.TokenCallback;
import com.example.theodhor.retrofit2.interfaces.TopCateCallback;
import com.example.theodhor.retrofit2.interfaces.TopCategorySelectionCallback;
import com.example.theodhor.retrofit2.net.ProductList;
import com.example.theodhor.retrofit2.net.TopProductModel;

import java.util.ArrayList;
import java.util.Locale;

import ai.api.AIListener;
import ai.api.AIServiceException;
import ai.api.android.AIConfiguration;
import ai.api.android.AIService;
import ai.api.model.AIError;
import ai.api.model.AIRequest;
import ai.api.model.AIResponse;
import ai.api.model.Result;
import retrofit2.Response;

public class AgentActivity extends AppCompatActivity implements AIListener, TopCateCallback,
        TopCategorySelectionCallback, ProductDataCallback,TokenCallback {
    private Button listenButton;
    ImageView searchbtn;
    ArrayList<ActivityModel> chatBotResponseList;
    TextToSpeech t1;
    private AIService aiService;
    private EditText querystringet;
    LayoutInflater layoutInflater;
    final AIConfiguration config = new AIConfiguration("841360bf191d408d81396c775a7efb83",
            AIConfiguration.SupportedLanguages.English,
            AIConfiguration.RecognitionEngine.System);
    private AzureCommunicator communicator;
    LinearLayout chatView;
    private RecyclerView recyclerView;
    private ChatAdapter mAdapter;
    private boolean isDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        chatBotResponseList = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapter = new ChatAdapter(this, chatBotResponseList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        communicator = new AzureCommunicator(this);
        communicator.getToken("Bearer 4C4V4yUMvgI.cwA.cYw.qakgvIUDhYbrydkQgY59UniNz7HxTi5luInXlweDEZ8");
       // aiService = AIService.getService(this, config);
       // aiService.setListener(this);
        listenButton = (Button) findViewById(R.id.listenButton);
        querystringet = (EditText) findViewById(R.id.querystringet);
        searchbtn = (ImageView) findViewById(R.id.searchbtn);
       /* listenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aiService.startListening();
            }
        });
*/

        ActivityModel chatPojoModel = new ActivityModel();
        chatPojoModel.setType("welcomemessage");
        chatBotResponseList.add(chatPojoModel);
        mAdapter.notifyDataSetChanged();

        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ActivityModel chatPojoModel = new ActivityModel();
                chatPojoModel.setType("userinput");
                chatPojoModel.setUserInput(querystringet.getText().toString());
                chatBotResponseList.add(chatPojoModel);
                mAdapter.notifyDataSetChanged();
                communicator.sendActivityToBot(convserationToken,querystringet.getText().toString(),conservationId);
                querystringet.setText("");
/*
                sendRequest(querystringet.getText().toString());
                ChatPojoModel chatPojoModel = new ChatPojoModel();
                chatPojoModel.setResponse(null);
                chatPojoModel.setType("userinput");
                chatPojoModel.setUserInput(querystringet.getText().toString());
                chatBotResponseList.add(chatPojoModel);
                mAdapter.notifyDataSetChanged();
                querystringet.setText("");
*/


            }
        });

        t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.UK);
                }
            }
        });

        layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public void onResult(ai.api.model.AIResponse response) {
        Result result = response.getResult();
        if (result.getAction() != null && !result.getAction().equalsIgnoreCase("")) {
            if (result.getAction().equalsIgnoreCase("showTopCategories")) {
                communicator.getTopCategories();
            } else if (result.getAction().equalsIgnoreCase("search")) {
                if (result.getParameters() != null && result.getParameters().get("product-filter")
                        != null && !result.getParameters().get("product-filter").toString()
                        .equalsIgnoreCase("")) {
                    communicator.searchProduct(result.getResolvedQuery());
                } else {
                    communicator.getProductByCat(result.getResolvedQuery());
                }
            }
        }

    }

    @Override
    public void onError(ai.api.model.AIError error) {
    }

    @Override
    public void onAudioLevel(float level) {
    }

    @Override
    public void onListeningStarted() {
    }

    @Override
    public void onListeningCanceled() {
    }

    @Override
    public void onListeningFinished() {
    }


    //AIRequest should have query OR event
    private void sendRequest(String queryString) {

        final AsyncTask<String, Void, AIResponse> task = new AsyncTask<String, Void, AIResponse>() {
            private AIError aiError;

            @Override
            protected AIResponse doInBackground(final String... params) {
                final AIRequest request = new AIRequest();
                String query = params[0];
                if (!TextUtils.isEmpty(query))
                    request.setQuery(query);
                try {
                    return aiService.textRequest(request);
                } catch (final AIServiceException e) {
                    aiError = new AIError(e);
                    return null;
                }
            }

            @Override
            protected void onPostExecute(final AIResponse response) {
                if (response != null) {
                    onResult(response);
                } else {
                    onError(aiError);
                }
            }
        };
        task.execute(queryString);
    }

    @Override
    public void onSuccess(Response<TopProductModel> topProductModelResponse) {
     /*   ChatPojoModel chatPojoModel = new ChatPojoModel();
        chatPojoModel.setResponse(topProductModelResponse);
        chatPojoModel.setType("showCategories");
        chatPojoModel.setUserInput("");
        chatBotResponseList.add(chatPojoModel);
        mAdapter.notifyDataSetChanged();*/
    }

    @Override
    public void onFailure(Throwable throwable) {

    }

    @Override
    public void onCategorySelection(Response<TopProductModel> topProductModelResponse) {
     /*   ChatPojoModel chatPojoModel = new ChatPojoModel();
        chatPojoModel.setResponse(topProductModelResponse);
        chatPojoModel.setType("productsTitles");
        chatPojoModel.setUserInput("");
        chatBotResponseList.add(chatPojoModel);
        mAdapter.notifyDataSetChanged();*/
    }

    @Override
    public void onCategoryFailure(Throwable throwable) {

    }

    @Override
    public void productCallback(Response<ProductList> response) {
      /*  ChatPojoModel chatPojoModel = new ChatPojoModel();
        chatPojoModel.setResponse(null);
        chatPojoModel.setProductSearchResponse(response);
        if (!isDetail)
            chatPojoModel.setType("productSearch");
        else
            chatPojoModel.setType("productDetail");
        chatPojoModel.setUserInput("");
        chatBotResponseList.add(chatPojoModel);

        mAdapter.notifyDataSetChanged();
        Toast.makeText(AgentActivity.this, response.body().getValue().size() + "Products Found.",
                Toast.LENGTH_LONG).show();*/

    }

    @Override
    public void productFailure(Throwable throwable) {

    }

    @Override
    public void fetchDetails(String id) {
        isDetail = true;
        communicator.getProductDetail(id);
    }
    String convserationToken="",conservationId="";
    @Override
    public void getToken(Response<ApiTokenModel> apiTokenModel) {
        convserationToken=apiTokenModel.body().getToken();
        communicator.startConversation(apiTokenModel.body().getToken());
    }

    @Override
    public void startConvsersationCallbacl(Response<StartConversationModel> apiTokenModel) {
        conservationId=apiTokenModel.body().getConversationId();

    }
 String watermark;
    @Override
    public void fetchResponseFromBot(Response<ConvserationModel> response) {
        Log.e("Success11111", "Success"+response.body().id);
        try {
            Thread.sleep(2000);
        } catch (Exception e) {

        }
        String[] responseArray=((ConvserationModel) ((Response) response).body()).id.split("\\|");
        watermark=responseArray[1];
        communicator.getResponseFromBot(convserationToken,watermark,responseArray[0]);

    }

    @Override
    public void showBotResponse(Response<ActivityModel> response) {
        Log.e("Success222222222", "Success"+response.body().getActivities().size());
        if(null!=response.body().getActivities() && response.body().getActivities().size()>0) {
            Log.e("Success33333", "Success"+response);
            ActivityModel chatPojoModel = new ActivityModel();
            chatPojoModel.setType("messagfrombot");
            chatPojoModel.setActivities(response.body().getActivities());
            chatPojoModel.setWatermark(response.body().getWatermark());
            watermark = response.body().getWatermark();
            chatBotResponseList.add(chatPojoModel);
            Log.e("Success4444", mAdapter+"");
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void productSelectionCallback(String productId) {
        communicator.sendActivityToBot(convserationToken,productId,conservationId);

    }
}
