package com.utn.parcial.Fragment

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.utn.parcial.R
import com.utn.parcial.ViewModel.MacetaViewModel

class macetaFragment : Fragment() {

    lateinit var v: View
    lateinit var viewPager: ViewPager2
    lateinit var tabLayout: TabLayout

   private val titles =
       //arrayOf("Movies", "Events", "Tickets")
       arrayOf("Galeria", "Bitacora")

    companion object {
        fun newInstance() = macetaFragment()
    }

    private lateinit var viewModel: MacetaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        v = inflater.inflate(R.layout.maceta_fragment, container, false)

        tabLayout = v.findViewById(R.id.tab_layout)

        viewPager = v.findViewById(R.id.view_pager)

        return v

    }

    ///////////Tolbar ////////////////
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.lista_toolbar, menu)
        super.onCreateOptionsMenu(menu, inflater)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {





        val id = when(item.itemId) {

            R.id.action_delete -> Snackbar.make(v, "Borrar", Snackbar.LENGTH_SHORT).show()

            else -> ""
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    /////////////////////////////////////

    override fun onStart() {
        super.onStart()

       // viewModel.id = macetaFragmentArgs.fromBundle(arguments!!).plantaId.toString()
        viewPager.setAdapter(createCardAdapter())
        // viewPager.isUserInputEnabled = false
        //viewModel.context_maceta.value = context
        TabLayoutMediator(tabLayout, viewPager, TabLayoutMediator.TabConfigurationStrategy { tab, position ->
            when (position) {
                0 -> tab.text = "Galeria"
                1 -> tab.text = "Bitacora"
               // 2 -> tab.text = "Tab3"
                else -> tab.text = "undefined"
            }
        }).attach()
    }



    private fun createCardAdapter(): ViewPagerAdapter? {
        return ViewPagerAdapter(requireActivity())
    }


    class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
        override fun createFragment(position: Int): Fragment {

            return when (position) {
                0 -> galeriaFragment()
                1 -> BitacoraFragment()
               // 2 -> fragment3()

                else -> galeriaFragment()
            }
        }

        override fun getItemCount(): Int {
            return TAB_COUNT
        }


        companion object {
            //private const val TAB_COUNT = 3
            private const val TAB_COUNT = 2
        }

    }

        override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
            //viewModel = ViewModelProviders.of(this).get(MacetaViewModel::class.java)
           viewModel = ViewModelProvider(requireActivity()).get(MacetaViewModel::class.java)

        // TODO: Use the ViewModel
    }


}
