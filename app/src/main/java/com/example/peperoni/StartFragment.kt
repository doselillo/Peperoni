package com.example.peperoni

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.peperoni.databinding.FragmentStartBinding
import com.example.peperoni.model.PizzaViewModel



class StartFragment : Fragment() {

    private val sharedViewModel: PizzaViewModel by activityViewModels()

    private var binding: FragmentStartBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragmentBinding = FragmentStartBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.startFragment = this
    }

    fun orderPizza(size: String){
        sharedViewModel.setSize(size)
        if(sharedViewModel.hasNoIngredientSet()){sharedViewModel.setIngredient("Chicken")}
        findNavController().navigate(R.id.action_startFragment_to_ingredientFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}