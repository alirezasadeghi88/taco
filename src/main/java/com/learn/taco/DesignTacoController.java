package com.learn.taco;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {
    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        List<com.learn.taco.Ingredient> ingredients = Arrays.asList(
                new com.learn.taco.Ingredient("FLTO", "Flour Tortilla", com.learn.taco.Ingredient.Type.PROTEIN.WRAP),
                new com.learn.taco.Ingredient("COTO", "Corn Tortilla", com.learn.taco.Ingredient.Type.PROTEIN.WRAP),
                new com.learn.taco.Ingredient("GRBF", "Ground Beef", com.learn.taco.Ingredient.Type.PROTEIN.PROTEIN),
                new com.learn.taco.Ingredient("CARN", "Carnitas", com.learn.taco.Ingredient.Type.PROTEIN.PROTEIN),
                new com.learn.taco.Ingredient("TMTO", "Diced Tomatoes", com.learn.taco.Ingredient.Type.PROTEIN.VEGGIES),
                new com.learn.taco.Ingredient("LETC", "Lettuce", com.learn.taco.Ingredient.Type.PROTEIN.VEGGIES),
                new com.learn.taco.Ingredient("CHED", "Cheddar", com.learn.taco.Ingredient.Type.PROTEIN.CHEESE),
                new com.learn.taco.Ingredient("JACK", "Monterrey Jack", com.learn.taco.Ingredient.Type.PROTEIN.CHEESE),
                new com.learn.taco.Ingredient("SLSA", "Salsa", com.learn.taco.Ingredient.Type.PROTEIN.SAUCE),
                new com.learn.taco.Ingredient("SRCR", "Sour Cream", com.learn.taco.Ingredient.Type.PROTEIN.SAUCE)
        );
        com.learn.taco.Ingredient.Type[] types = com.learn.taco.Ingredient.Type.values();
        for (com.learn.taco.Ingredient.Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),

            filterByType(ingredients, type));
        }
    }
    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order() {
        return new TacoOrder();
    }

    @ModelAttribute(name = "taco")
    public com.learn.taco.Taco taco() {
        return new com.learn.taco.Taco();
    }
    @GetMapping
    public String showDesignForm() {
        return "design";
    }
    private Iterable<com.learn.taco.Ingredient> filterByType(
            List<com.learn.taco.Ingredient> ingredients, com.learn.taco.Ingredient.Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}
