package com.example.findissues.ui.fragments

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.findissues.R
import com.example.findissues.databinding.FragmentPullsBinding

class PullsFragment : Fragment() {

    private var _binding: FragmentPullsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPullsBinding.inflate(inflater, container, false)
        binding.toolbar.root.title = resources.getString(R.string.pulls)
        binding.toolbar.root.inflateMenu(R.menu.menu_pulls)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_pulls, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.open -> {
                Toast.makeText(activity, "Open",Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.merged -> {
                Toast.makeText(activity, "Merged",Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.close -> {
                Toast.makeText(activity, "Closed",Toast.LENGTH_SHORT).show()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}