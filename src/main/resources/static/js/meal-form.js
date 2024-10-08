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
    document.getElementById('mealForm').addEventListener('submit', function(e) {
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

    document.getElementById('logoutForm').addEventListener('submit', function(e) {

    });


    // Cancel modal functionality
    const modal = document.getElementById("cancelModal");
    const cancelButton = document.querySelector('.button-main.btn-cancel.btn-submit');

    function showCancelConfirmation(event) {
        event.preventDefault(); // Prevent the default button action
        modal.style.display = "block";
    }

    function closeCancelModal() {
        modal.style.display = "none";
    }

    function confirmCancel() {
        const currentPath = window.location.pathname;
        const pathParts = currentPath.split('/');
        const encodedUsername = pathParts[1]; // This should be the encoded username
        const baseUrl = window.location.origin; // This will get the correct base URL dynamically
        window.location.href = `${baseUrl}/${encodedUsername}/meals/list`;
    }

    // Event listeners for modal functionality
    if (cancelButton) {
        cancelButton.addEventListener('click', showCancelConfirmation);
    }

    const closeModalButton = document.querySelector('.btn.btn-cancel');
    if (closeModalButton) {
        closeModalButton.addEventListener('click', closeCancelModal);
    }

    const confirmCancelButton = document.getElementById('yes');
    if (confirmCancelButton) {
        confirmCancelButton.addEventListener('click', confirmCancel);
    }


    // Close the modal if the user clicks outside of it
    window.addEventListener('click', function(event) {
        if (event.target == modal) {
            closeCancelModal();
        }
    });




});

