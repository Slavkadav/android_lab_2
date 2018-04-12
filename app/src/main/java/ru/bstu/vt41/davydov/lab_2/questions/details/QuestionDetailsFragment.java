package ru.bstu.vt41.davydov.lab_2.questions.details;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import ru.bstu.vt41.davydov.lab_2.R;
import ru.bstu.vt41.davydov.lab_2.model.QuestionDetail;
import ru.bstu.vt41.davydov.lab_2.services.QuestionService;


/**
 * Created by macbook on 23.03.2018.
 */

public class QuestionDetailsFragment extends Fragment {

    private QuestionDetail questionDetail;

    public static QuestionDetailsFragment newInstance(int questionIndex) {
        QuestionDetailsFragment details = new QuestionDetailsFragment();
        Bundle args = new Bundle();
        args.putInt("index", questionIndex);
        details.setArguments(args);
        return details;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        questionDetail = QuestionService.getQuestionDetail(this.getArguments().getInt("index"));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.question_detail, container, false);
        ImageView avatar = view.findViewById(R.id.questionAvatar);
//        try {
//            avatar.setImageBitmap(new ImageDownloadService().execute(questionDetail.getUserImageUrl()).get());
//        } catch (InterruptedException | ExecutionException e) {
//            e.printStackTrace();
//        }
//
        TextView questionHead = view.findViewById(R.id.textHead);
        questionHead.setText(questionDetail.getTitle());
//
        TextView questionText = view.findViewById(R.id.questionText);
        questionText.setText(String.valueOf(questionDetail.getText()));

        ListView answers = view.findViewById(R.id.answer_list);
        AnswersListAdapter answersListAdapter = new AnswersListAdapter(getActivity(), questionDetail.getAnswers());
        answers.setAdapter(answersListAdapter);

        return view;
    }

}
