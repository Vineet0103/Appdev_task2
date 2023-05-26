package com.example.triviaapp


import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import kotlinx.coroutines.Dispatchers.Main


class DoneAdapter (private val context: Context,
                  private val info: DoneFeed
): BaseAdapter() {
    override fun getCount(): Int {
        return 1
    }

    override fun getItem(position: Int): Any {
        return  position.toLong()
    }

    override fun getItemId(position: Int): Long {
        return  position.toLong()
    }

    override fun getView(position: Int, view: View?, viewGroup: ViewGroup?): View {
        val layoutInflater: LayoutInflater= LayoutInflater.from(context);
        var score=(info.qCorrectAnswers.toInt())*10-info.qNegative.toInt()
        var attempted =(info.qCorrectAnswers.toInt()+info.qNegative.toInt())
        val statRow: View = layoutInflater.inflate(R.layout.donelist, viewGroup, false)
        statRow.findViewById<TextView>(R.id.textView8).text = "No of Questions: ${info.qNumbers}"
        statRow.findViewById<TextView>(R.id.q_number).text = "Attempted Questions: ${attempted}"
        statRow.findViewById<TextView>(R.id.textView9).text = "Correct Answers: ${info.qCorrectAnswers}"
        statRow.findViewById<TextView>(R.id.textView10).text = "Negative marks: ${info.qNegative}"
        statRow.findViewById<TextView>(R.id.score).text = "SCORE: ${score}/100"
        val cancelButton: ImageButton = statRow.findViewById(R.id.restart)

        cancelButton.setOnClickListener {
            Questions.questionNr=0
            Questions.isCorrect =0
            Questions.isFailed=0
            MainActivity2.flag=0
            Noofquestions.nextflag=0
            val previousActivityIntent = Intent(context, MainActivity2::class.java)
            context.startActivity(previousActivityIntent)
        }

        return statRow
    }

}



