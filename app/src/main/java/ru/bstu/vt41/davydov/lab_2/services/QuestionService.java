package ru.bstu.vt41.davydov.lab_2.services;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import ru.bstu.vt41.davydov.lab_2.model.Answer;
import ru.bstu.vt41.davydov.lab_2.model.Question;
import ru.bstu.vt41.davydov.lab_2.model.QuestionDetail;

public class QuestionService {

    public static final String QUESTIONS_API = "http://10.0.2.2:8080/questions/";
    private static OkHttpClient client = new OkHttpClient();

//    public static List<Question> getQuestions(MyCallback callback) {
//
//        final ArrayList<Question> questions = new ArrayList<>();
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                e.printStackTrace();
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                try {
//                    JSONObject object = new JSONObject(response.body().string());
//                    JSONArray array = object.getJSONArray("questions");
//                    for (int i = 0; i < array.length(); i++) {
//                        Question question = parseQuestion(array.getJSONObject(i));
//                        questions.add(question);
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//        return questions;
//
//    }

    public static List<Question> parseQuestions(String object) {
        try {
            JSONObject jsonObject = new JSONObject(object);
            List<Question> questions = new ArrayList<>();
            JSONArray array = jsonObject.getJSONArray("questions");
            for (int i = 0; i < array.length(); i++) {
                Question question = parseQuestion(array.getJSONObject(i));
                questions.add(question);
            }
            return questions;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Question parseQuestion(JSONObject jsonObject) {
        try {
            Question question = new Question();
            question.setId(jsonObject.getLong("id"));
            question.setAnswersCount(jsonObject.getInt("answersCount"));
            question.setText(jsonObject.getString("text"));
            question.setTitle(jsonObject.getString("title"));
            question.setUserImageUrl(jsonObject.getString("userImageUrl"));
            return question;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static QuestionDetail getQuestionDetail(int id) {
        final Request request = new Request.Builder().url(QUESTIONS_API + id).build();
        final QuestionDetail questionDetail = new QuestionDetail();
        new OkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    JSONObject object = new JSONObject(response.body().string());
                    questionDetail.setId(object.getLong("id"));
                    questionDetail.setText(object.getString("text"));
                    questionDetail.setTitle(object.getString("title"));
                    questionDetail.setUserImageUrl(object.getString("userImageUrl"));
                    JSONArray answers = object.getJSONArray("answers");
                    for (int i = 0; i < answers.length(); i++) {
                        Answer answer = new Answer();
                        answer.setAnswer(answers.getJSONObject(i).getString("answer"));
                        answer.setUserName(answers.getJSONObject(i).getString("userName"));
                        questionDetail.getAnswers().add(answer);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        return questionDetail;
    }


}
