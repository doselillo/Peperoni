package com.example.peperoni.model


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

private const val PRICE_PIZZA = 6.00
private const val PRICE_PER_SAME_DAY_PICKUP = 2.00

class PizzaViewModel: ViewModel(){

    private val _size= MutableLiveData<String>()
    val size: LiveData<String> = _size

    private val _ingredient = MutableLiveData<String>()
    val ingredient: LiveData<String> = _ingredient

    private val _date = MutableLiveData<String>()
    val date: LiveData<String> = _date

    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name

    private val _address = MutableLiveData<String>()
    val address: LiveData<String> = _address

    private val _price = MutableLiveData<Double>()
    val price: LiveData<String> = Transformations.map(_price) {
        NumberFormat.getCurrencyInstance().format(it)
    }

    private val _cost = MutableLiveData<Double>()
    val cost: LiveData<Double> = _cost

    val dateOptions = getdateOptions()

    init{
        resetOrder()
    }

    fun setSize(size: String){
        _size.value = size
        updatePrice()
    }

    fun setIngredient(ingredient: String){
        _ingredient.value = ingredient

    }

    fun setDate(date: String) {
        _date.value = date
        updatePrice()
    }

    fun setName(name: String){
        _name.value = name
    }

    fun setAddress(address: String) {
        _address.value = address
    }

    fun setCost(cost: Double){
        _cost.value = cost
        updatePrice()
    }

    fun hasNoIngredientSet(): Boolean{
        return _ingredient.value.isNullOrEmpty()
    }

    private fun getdateOptions(): List<String>{
        val options = mutableListOf<String>()
        val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())
        val calendar = Calendar.getInstance()
        repeat(4){
            options.add(formatter.format(calendar.time))
            calendar.add(Calendar.DATE, 1)
        }
        return options
    }

    fun resetOrder(){
        _size.value = ""
        _ingredient.value = ""
        _date.value = dateOptions[0]
        _name.value = ""
        _address.value = ""
        _price.value = 0.0
        _cost.value = 0.0
    }

    private fun updatePrice(){

        var calculatedPrice = (_cost.value ?: 0.0)
        if (dateOptions[0] == _date.value) {
            calculatedPrice += PRICE_PER_SAME_DAY_PICKUP
        }
        _price.value = calculatedPrice

    }



}


