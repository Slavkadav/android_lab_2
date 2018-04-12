package ru.bstu.vt41.davydov.lab_2.questions;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import ru.bstu.vt41.davydov.lab_2.R;
import ru.bstu.vt41.davydov.lab_2.model.Question;
import ru.bstu.vt41.davydov.lab_2.services.QuestionService;

/**
 * Created by macbook on 23.03.2018.
 */

public class QuestionsFragment extends ListFragment {
    //Отвратительно, но пока что сойдет

    private OnSelectQuestionListener listener;
    private OkHttpClient client;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final Request request = new Request.Builder().url(getString(R.string.api_path)).build();
        new OkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final List<Question> questions = QuestionService.parseQuestions(response.body().string());
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        QuestionListAdapter questionListAdapter = new QuestionListAdapter(getActivity(), questions);
                        setListAdapter(questionListAdapter);
                    }
                });

            }
        });

    }

    //API >= 23
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (OnSelectQuestionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnSelectQuestionListener");
        }
    }

    //API < 23
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            listener = (OnSelectQuestionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnSelectQuestionListener");
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        listener.onQuestionSelected(position);

    }

    public interface OnSelectQuestionListener {
        void onQuestionSelected(int index);
    }
}
