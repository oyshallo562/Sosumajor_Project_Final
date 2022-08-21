package com.sosu.project

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/*
onCreateViewHolder(ViewGroup parent, int viewType)	viewType 형태의 아이템 뷰를 위한 뷰홀더 객체 생성.
onBindViewHolder(ViewHolder holder, int position)	position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
getItemCount()	전체 아이템 갯수 리턴.
 */

class QuotesPagerAdapter(
    private val quotes: List<Quote>,
    private val isNameRevealed: Boolean
) : // 리사이클러뷰 어뎁터를 상속받으며 제너릭으로 뷰홀더를 설정해
    RecyclerView.Adapter<QuotesPagerAdapter.QuoteViewHolder>() {

    // 뷰 홀더로 구성해둔 레이아웃 설정(인플레이트)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder =
        QuoteViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.quote_item, parent, false)
        )

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        val actualPosition = position % quotes.size // 무한 스크롤을 할 것 이므로 사이즈로 나눈 나머지로 위치 정함.
        holder.bind(quotes[actualPosition], isNameRevealed)
    }

    override fun getItemCount(): Int = Int.MAX_VALUE //quotes.size

    // 뷰 홀더 클래스 (내부에 정의줌 해주었음)
    class QuoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // itemView : 우리가 정의 해준 레이아웃 뷰.
        private val quoteTextView: TextView = itemView.findViewById(R.id.quoteTextView)
        private val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)

        @SuppressLint("SetTextI18n")
        fun bind(quote: Quote, isNameRevealed: Boolean) {
            // 어떻게 랜더링 할 것인가
            quoteTextView.text = "\"${quote.quote}\"" // 명언 내용 (큰따옴표 추가)

            // 원격 isNameRevealed 에 따라 분기 (Firebase 에서 설정된 불값을 가져와서 처리)
            if (isNameRevealed) {
                nameTextView.text = "- ${quote.name}" // 작가 (작대기 추가)
                nameTextView.visibility = View.VISIBLE
            } else {
                nameTextView.visibility = View.GONE
            }
        }
    }
}