document.addEventListener('DOMContentLoaded', function() {
    let ingredients = [];

    window.addIngredient = function() {
        const name = document.getElementById('ingredientName').value;
        const quantity = document.getElementById('ingredientQuantity').value;
        const measureSelect = document.getElementById('ingredientMeasure');
        const measureId = measureSelect.value;
        const measureText = measureSelect.options[measureSelect.selectedIndex].text;

        if (name && quantity && measureId) {
            ingredients.push({ name, quantity, measureId, measureText });
            updateIngredientsList();
            clearIngredientInputs();
        } else {
            alert('Please fill all ingredient fields');
        }
    }

    function updateIngredientsList() {
        const list = document.getElementById('ingredientsList');
        list.innerHTML = ingredients.map((ing, index) =>
            `<div>${ing.name} - ${ing.quantity} ${ing.measureText} 
             <button type="button" onclick="removeIngredient(${index})">Remove</button></div>`
        ).join('');
    }

    window.removeIngredient = function(index) {
        ingredients.splice(index, 1);
        updateIngredientsList();
    }

    function clearIngredientInputs() {
        document.getElementById('ingredientName').value = '';
        document.getElementById('ingredientQuantity').value = '';
        document.getElementById('ingredientMeasure').value = '';
    }

    document.querySelector('form').addEventListener('submit', function(e) {
        e.preventDefault();

        // Add hidden inputs for ingredients
        ingredients.forEach((ing, index) => {
            this.innerHTML += `
                <input type="hidden" name="ingredients[${index}].ingredient" value="${ing.name}">
                <input type="hidden" name="ingredients[${index}].quantity" value="${ing.quantity}">
                <input type="hidden" name="ingredients[${index}].measure.id" value="${ing.measureId}">
            `;
        });

        this.submit();
    });
});