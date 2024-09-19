// Global variables
let ingredients = [];

// Function definitions
function loadExistingIngredients() {
    const existingIngredients = document.querySelectorAll('[data-ingredient]');
    existingIngredients.forEach(ing => {
        ingredients.push({
            ingredient: ing.dataset.ingredient,
            quantity: ing.dataset.quantity,
            measureId: ing.dataset.measureId,
            measureText: ing.dataset.measureText
        });
    });
    updateIngredientsList();
}

function updateIngredientsList() {
    const list = document.getElementById('ingredientsList');
    list.innerHTML = ingredients.map((ing, index) =>
        `<div class="ingredient-item">
            <span class="ingredient-text">${ing.ingredient} - ${ing.quantity} ${ing.measureText}</span>
            <button type="button" class="button-remove" onclick="removeIngredient(${index})">Remove</button>
        </div>`
    ).join('');
}

function addIngredient() {
    const ingredient = document.getElementById('ingredientName').value;
    const quantity = document.getElementById('ingredientQuantity').value;
    const measureSelect = document.getElementById('ingredientMeasure');
    const measureId = measureSelect.value;
    const measureText = measureSelect.options[measureSelect.selectedIndex].text;

    if (ingredient && quantity && measureId) {
        ingredients.push({ ingredient, quantity, measureId, measureText });
        updateIngredientsList();
        clearIngredientInputs();
    } else {
        alert('Please fill all ingredient fields');
    }
}

function removeIngredient(index) {
    ingredients.splice(index, 1);
    updateIngredientsList();
}

function clearIngredientInputs() {
    document.getElementById('ingredientName').value = '';
    document.getElementById('ingredientQuantity').value = '';
    document.getElementById('ingredientMeasure').selectedIndex = 0;
}

// Event listeners
document.addEventListener('DOMContentLoaded', function() {
    console.log("DOM fully loaded and parsed");

    // Check if it's an update scenario
    const isUpdateScenario = document.querySelector('[data-ingredient]') !== null;

    if (isUpdateScenario) {
        console.log("Update scenario detected");
        loadExistingIngredients();
    } else {
        console.log("New meal scenario");
    }

    // Add event listener for the Add Ingredient button
    const addButton = document.querySelector('.btn-right[onclick="addIngredient()"]');
    if (addButton) {
        addButton.onclick = addIngredient;
    }

    // Form submission event listener
    document.querySelector('form').addEventListener('submit', function(e) {
        e.preventDefault();

        // Remove any existing ingredient inputs
        this.querySelectorAll('input[name^="ingredients"]').forEach(el => el.remove());

        // Remove the hidden div containing old ingredient data
        const oldIngredientsDiv = this.querySelector('div[style="display: none;"]');
        if (oldIngredientsDiv) {
            oldIngredientsDiv.remove();
        }

        ingredients.forEach((ing, index) => {
            const ingredientInput = document.createElement('input');
            ingredientInput.type = 'hidden';
            ingredientInput.name = `ingredients[${index}].ingredient`;
            ingredientInput.value = ing.ingredient;
            this.appendChild(ingredientInput);

            const quantityInput = document.createElement('input');
            quantityInput.type = 'hidden';
            quantityInput.name = `ingredients[${index}].quantity`;
            quantityInput.value = ing.quantity;
            this.appendChild(quantityInput);

            const measureInput = document.createElement('input');
            measureInput.type = 'hidden';
            measureInput.name = `ingredients[${index}].measure.id`;
            measureInput.value = ing.measureId;
            this.appendChild(measureInput);
        });

        this.submit();
    });
});