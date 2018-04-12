package ru.bstu.vt41.davydov.lab_2.questions.details;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import ru.bstu.vt41.davydov.lab_2.R;
import ru.bstu.vt41.davydov.lab_2.model.Answer;

/**
 * Created by macbook on 06.04.2018.
 */

public class AnswersListAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<Answer> data;

    public AnswersListAdapter(Context context, List<Answer> answers) {
        this.data = answers;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View answersView = convertView == null ? inflater.inflate(R.layout.answer_list_item, parent, false) : convertView;
        Answer answer = (Answer) getItem(position);

        TextView authorView = answersView.findViewById(R.id.author);
        authorView.setText(answer == null ? "Имя пользователя" : answer.getUserName());

        TextView userAnswer = answersView.findViewById(R.id.user_answer);
        userAnswer.setText(answer == null ? "Ответ" : answer.getAnswer());

        return answersView;
    }
}
