package com.example.triviaapp

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.triviaapp.AllResults
import com.example.triviaapp.AnswerAdapter
import com.example.triviaapp.DoneAdapter
import com.example.triviaapp.DoneFeed
import com.example.triviaapp.JoinedFeed
import com.example.triviaapp.Questions.Companion.allJoined
import com.example.triviaapp.Questions.Companion.isCorrect
import com.example.triviaapp.Questions.Companion.isFailed
import com.example.triviaapp.Questions.Companion.questionNr
import com.example.triviaapp.R
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.google.gson.GsonBuilder
import kotlin.math.abs


class Questions : AppCompatActivity() {
    companion object {
        var allJoined: ArrayList<JoinedFeed> = ArrayList();
        var questionNr: Int = 0;
        var selectedAnswers: ArrayList<String> = ArrayList()
        var isCorrect: Int = 0;
        var isFailed: Int = 0;
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)

    var endpoint :String
        if(MainActivity2.flag==1 && Noofquestions.nextflag==1) {
            endpoint =
                "https://opentdb.com/api.php?amount=10&category=21&difficulty=easy&type=multiple"
        }
        else if(MainActivity2.flag==1 && Noofquestions.nextflag==2) {
            endpoint =
                "https://opentdb.com/api.php?amount=10&category=21&difficulty=medium&type=multiple"
        }
        else if(MainActivity2.flag==1 && Noofquestions.nextflag==3) {
            endpoint =
                "https://opentdb.com/api.php?amount=10&category=21&difficulty=hard&type=multiple"
        }
        else if(MainActivity2.flag==2 && Noofquestions.nextflag==1){
            endpoint = "https://opentdb.com/api.php?amount=10&category=15&difficulty=easy&type=multiple"
        }
        else if(MainActivity2.flag==2 && Noofquestions.nextflag==2){
            endpoint = "https://opentdb.com/api.php?amount=10&category=15&difficulty=medium&type=multiple"
        }
        else if(MainActivity2.flag==2 && Noofquestions.nextflag==3){
            endpoint = "https://opentdb.com/api.php?amount=10&category=15&difficulty=hard&type=multiple"
        }
        else if(MainActivity2.flag==3 && Noofquestions.nextflag==1){
            endpoint ="https://opentdb.com/api.php?amount=10&category=11&difficulty=easy&type=multiple"
        }
        else if(MainActivity2.flag==3 && Noofquestions.nextflag==2){
            endpoint ="https://opentdb.com/api.php?amount=10&category=11&difficulty=medium&type=multiple"
        }
        else if(MainActivity2.flag==3 && Noofquestions.nextflag==3){
            endpoint ="https://opentdb.com/api.php?amount=10&category=11&difficulty=hard&type=multiple"
        }
        else if(MainActivity2.flag==4 && Noofquestions.nextflag==1){
            endpoint ="https://opentdb.com/api.php?amount=10&category=18&difficulty=easy&type=multiple"
        }
        else if(MainActivity2.flag==4 && Noofquestions.nextflag==2){
            endpoint ="https://opentdb.com/api.php?amount=10&category=18&difficulty=medium&type=multiple"
        }
        else if(MainActivity2.flag==4 && Noofquestions.nextflag==3){
            endpoint ="https://opentdb.com/api.php?amount=10&category=18&difficulty=hard&type=multiple"
        }
        else if(MainActivity2.flag==5 && Noofquestions.nextflag==1){
            endpoint ="https://opentdb.com/api.php?amount=10&category=31&difficulty=easy&type=multiple"
        }
        else if(MainActivity2.flag==5 && Noofquestions.nextflag==2){
            endpoint ="https://opentdb.com/api.php?amount=10&category=31&difficulty=medium&type=multiple"
        }
        else if(MainActivity2.flag==5 && Noofquestions.nextflag==3){
            endpoint ="https://opentdb.com/api.php?amount=10&category=31&difficulty=hard&type=multiple"
        }
        else if(MainActivity2.flag==6 && Noofquestions.nextflag==1){
            endpoint ="https://opentdb.com/api.php?amount=10&category=20&difficulty=easy&type=multiple"
        }
        else if(MainActivity2.flag==6 && Noofquestions.nextflag==2){
            endpoint ="https://opentdb.com/api.php?amount=10&category=20&difficulty=medium&type=multiple"
        }
        else if(MainActivity2.flag==6 && Noofquestions.nextflag==3){
            endpoint ="https://opentdb.com/api.php?amount=10&category=20&difficulty=hard&type=multiple"
        }

else {
            endpoint = "https://opentdb.com/api.php?amount=10&type=multiple"
        }
        val questions: ArrayList<String> = ArrayList();
        val allanswers: ArrayList<ArrayList<String>> = ArrayList();
        val allcorrectanswers: ArrayList<String> = ArrayList();
        val donelayout: ConstraintLayout = findViewById(R.id.done);
        donelayout.visibility = View.GONE
       // supportActionBar?.hide();

        val httpAsync = endpoint
            .httpGet()
            .responseString { request, response, result ->
                when (result) {
                    is Result.Failure -> {
                        val ex = result.getException()
                        println(ex)
                    }

                    is Result.Success -> {
                        val data: String = result.get()
                        val gson = GsonBuilder().create()

                        val mainData: AllResults = gson.fromJson(data, AllResults::class.java)
                        for ((index, value) in mainData.results.withIndex()) {

                            val mainFeed = mainData.results;
                            val question = mainFeed[index].question;
                            questions.add(question)

                            val answers = mainFeed[index].incorrect_answers
                            answers.add((0..3).random(), mainFeed[index].correct_answer);
                            allanswers.add(answers);

                            val canswers = mainFeed[index].correct_answer;
                            allcorrectanswers.add(canswers);
                        }
                    }
                }
            }

        httpAsync.join()

        allJoined.add(
            JoinedFeed(
                questions = questions,
                answers = allanswers,
                correct_answers = allcorrectanswers
            )
        )
        startQuiz()
    }

    private fun startQuiz() {
        val nextbtn = findViewById<ImageButton>(R.id.next_btn);
        val totalnum: TextView = findViewById<TextView>(R.id.total_num);
        val qnum: TextView = findViewById(R.id.textView6)
        val mainquestion: TextView = findViewById<TextView>(R.id.main_question);
        val donelayout: ConstraintLayout = findViewById(R.id.done);
        val quizlayout: ConstraintLayout = findViewById(R.id.quiz);
        val donepop: ListView = findViewById(R.id.done_pop);
        val nextarrow: ImageView = findViewById(R.id.next_arrow)
        var questionNum = questionNr;
        val currentQuestion = allJoined[0].questions[questionNr];
        val answerListView: ListView = findViewById(R.id.answers_container);

        questionNum++;

        totalnum.text = "${questionNum.toString()}/${allJoined[0].questions.count()}";
        qnum.text = "${questionNum.toString()}"
        mainquestion.text = currentQuestion;
        var qanswers: ArrayList<String> = allJoined[0].answers[questionNr];
        setAnswers(qanswers)

        answerListView.setOnItemClickListener { parent, view, position, id ->
            val clickedID = id.toInt()
            val correctanswer = allJoined[0].correct_answers[questionNr]
            val selectedanswer = allJoined[0].answers[questionNr][clickedID]
            val answerIsCorrect = selectedanswer == correctanswer;

            if (answerIsCorrect) {
                isCorrect++
            } else {
                isFailed--
            }

            if (questionNr == allJoined[0].questions.count() - 1) {
                quizlayout.visibility = View.GONE;
                donelayout.visibility = View.VISIBLE

                var info: DoneFeed = DoneFeed(
                    qNumbers = "${allJoined[0].questions.count()}",
                    qCorrectAnswers = "${isCorrect}",
                    qAttempted = "${10}",
                    qNegative = "${abs(isFailed)}"
                )

                donepop.adapter = DoneAdapter(this, info)
            } else {
                questionNum++;
                questionNr++
            }

            totalnum.text = "${questionNum.toString()}/${allJoined[0].questions.count()}"
            qnum.text = "${questionNum.toString()}"
            mainquestion.text = allJoined[0].questions[questionNr];

            val newAnswers = allJoined[0].answers[questionNr];
            setAnswers(newAnswers)
        }
        nextarrow.setOnClickListener {
            if (questionNr == allJoined[0].questions.count() - 1 && questionNum == 10) {
                quizlayout.visibility = View.GONE;
                donelayout.visibility = View.VISIBLE
                val info: DoneFeed = DoneFeed(
                    qNumbers = "${allJoined[0].questions.count()}",
                    qCorrectAnswers = "${isCorrect}",
                    qAttempted = "${10}",
                    qNegative = "${abs(isFailed)}"
                )
                donepop.adapter = DoneAdapter(this, info)
            } else {
                questionNum++;
                questionNr++
            }
                totalnum.text = "${questionNum.toString()}/${allJoined[0].questions.count()}"
                qnum.text = "${questionNum.toString()}"
                mainquestion.text = allJoined[0].questions[questionNr];
            val newAnswers2 = allJoined[0].answers[questionNr];
            setAnswers(newAnswers2)
        }


    }

    private fun setAnswers(qanswers: ArrayList<String>) {
        val answers: ListView = findViewById(R.id.answers_container);
        for ((index, value) in qanswers.withIndex()) {
            answers.adapter = AnswerAdapter(this, qanswers)
        }
    }
}

