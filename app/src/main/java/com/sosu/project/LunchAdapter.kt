package com.sosu.project

import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.regex.Matcher
import java.util.regex.Pattern


class LunchAdapter(val lunch: List<LunchModel>?, mainActivity: MainActivity) :
    RecyclerView.Adapter<LunchAdapter.LunchViewHolder>() {



    class LunchViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        // 괄호 패턴, 공백 패턴 생성
        private val PATTERN_BRACKET: Pattern = Pattern.compile("\\([^\\(\\)]+\\)")
        private val VOID = ""

        private fun deleteBracket(text: String): String? {
            var matcher: Matcher = PATTERN_BRACKET.matcher(text)
            var pureText = text
            var removeTextArea = String()
            while (matcher.find()) {
                val startIndex: Int = matcher.start()
                val endIndex: Int = matcher.end()
                removeTextArea = pureText.substring(startIndex, endIndex)
                pureText = pureText.replace(removeTextArea, VOID)
                matcher = PATTERN_BRACKET.matcher(pureText)
            }
            return pureText
        }

        var date: TextView
        var menu: TextView
        fun setItem(item: LunchModel) {
            date.text = item.mealDate
            val tmpmenu = deleteBracket(item.menu)
            menu.text = Html.fromHtml(tmpmenu)
        }

        init {
            date = itemView.findViewById(R.id.date)
            menu = itemView.findViewById(R.id.menu)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LunchViewHolder {
        val itemView: View =
            LayoutInflater.from(parent.context).inflate(R.layout.lunch_item, parent, false)
        return LunchViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: LunchViewHolder, position: Int) {
        val item = lunch!![position]
        holder.setItem(item)
    }

    override fun getItemCount(): Int {
        return lunch!!.count()
    }
}