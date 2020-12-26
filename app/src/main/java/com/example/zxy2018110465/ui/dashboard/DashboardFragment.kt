package com.example.zxy2018110465.ui.dashboard

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.fourth.model.CardMatchingGame
import com.example.zxy2018110465.R
import kotlinx.android.synthetic.main.fragment_dashboard.*



class DashboardFragment : Fragment(){
    companion object {
        var game: CardMatchingGame = CardMatchingGame(24)
    }


    lateinit var adapter: DashboardViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = DashboardViewModel(game)
        recyclerView.adapter = adapter

        val configure = resources.configuration
        if (configure.orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.layoutManager = GridLayoutManager(this.context, 4)
        } else {
            recyclerView.layoutManager = GridLayoutManager(this.context, 6)
        }

        adapter.setOnClickListener {
            game.chooseCardAtIndex(it)
            updateUI()
        }

        updateUI()

        reset.setOnClickListener {
            game.reset()
            updateUI()
        }
    }

    fun updateUI() {
        adapter.notifyDataSetChanged()
        score.text = String.format("%s%d", "Score:", game.score)
        score.text = "Score:" + game.score
    }

}
