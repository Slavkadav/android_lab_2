package ru.bstu.vt41.davydov.lab_2;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ru.bstu.vt41.davydov.lab_2.questions.QuestionsFragment;
import ru.bstu.vt41.davydov.lab_2.questions.details.QuestionDetailsFragment;

import static ru.bstu.vt41.davydov.lab_2.questions.QuestionsFragment.OnSelectQuestionListener;

public class MainActivity extends AppCompatActivity implements OnSelectQuestionListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.container, new QuestionsFragment())
                .commit();

    }


    @Override
    public void onQuestionSelected(int index) {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, QuestionDetailsFragment.newInstance(index))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //getFragmentManager().popBackStackImmediate();
    }
}
