package okhttp3.guide;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PostExample {
  public static final MediaType JSON
      = MediaType.parse("application/json; charset=utf-8");

  OkHttpClient client = new OkHttpClient();

  String postForm(String url, String player1, String player2) throws IOException {

    //https://blog.csdn.net/muyi_amen/article/details/58586605

    //Form表单格式的参数传递
    FormBody formBody = new FormBody
            .Builder()
            .add("player1",player1)
            .add("player2",player2)
            .build();

    Request request = new Request.Builder()
        .url(url)
        .post(formBody)
        .build();
    try (Response response = client.newCall(request).execute()) {
      return response.body().string();
    }
  }

  String post(String url, String json) throws IOException {

    //创建RequestBody对象，将参数按照指定的MediaType封装
    RequestBody body = RequestBody.create(JSON, json);
    Request request = new Request.Builder()
        .url(url)
        .post(body)
        .build();
    try (Response response = client.newCall(request).execute()) {
      return response.body().string();
    }
  }

  String bowlingJson(String player1, String player2) {

    //也可以使用JSONObject封装参数
    //JSONObject jsonObject = new JSONObject();
    //try {
    //  jsonObject.put("参数名","参数值");
    //} catch (JSONException e) {
    //  e.printStackTrace();
    //}

    return "{'winCondition':'HIGH_SCORE',"
        + "'name':'Bowling',"
        + "'round':4,"
        + "'lastSaved':1367702411696,"
        + "'dateStarted':1367702378785,"
        + "'players':["
        + "{'name':'" + player1 + "','history':[10,8,6,7,8],'color':-13388315,'total':39},"
        + "{'name':'" + player2 + "','history':[6,10,5,10,10],'color':-48060,'total':41}"
        + "]}";
  }

  public static void main(String[] args) throws IOException {
    PostExample example = new PostExample();
    //String json = example.bowlingJson("Jesse", "Jake");
    //String response = example.post("http://www.roundsapp.com/post", json);

    String response = example.postForm("http://www.roundsapp.com/post","Jesse", "Jake");

    System.out.println(response);
  }
}
