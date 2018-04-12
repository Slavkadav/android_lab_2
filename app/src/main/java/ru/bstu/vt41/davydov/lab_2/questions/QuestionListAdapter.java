package ru.bstu.vt41.davydov.lab_2.questions;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ru.bstu.vt41.davydov.lab_2.R;
import ru.bstu.vt41.davydov.lab_2.model.Question;

/**
 * Created by macbook on 16.03.2018.
 */

public class QuestionListAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<Question> data;

    public QuestionListAdapter(Context context, List<Question> questions) {
        this.data = questions;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {


        View questionView = view == null ? inflater.inflate(R.layout.list_item, viewGroup, false) : view;

        Question question = (Question) getItem(i);
        ImageView avatar = questionView.findViewById(R.id.avatar);
//        try {
//            avatar.setImageBitmap(new ImageDownloadService().execute(question.getUserImageUrl()).get());
//        } catch (InterruptedException | ExecutionException e) {
//            e.printStackTrace();
//        }

        TextView questionText = questionView.findViewById(R.id.question);
        questionText.setText(question.getTitle());

        TextView answerText = questionView.findViewById(R.id.answer);
        String answerTextString = String.valueOf(question.getAnswersCount()) + " ответов дано";
        answerText.setText(String.valueOf(answerTextString));

        return questionView;
    }
}
