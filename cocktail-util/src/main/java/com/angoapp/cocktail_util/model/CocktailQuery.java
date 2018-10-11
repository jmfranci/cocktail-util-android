package com.angoapp.cocktail_util.model;

public class CocktailQuery {
    private String id;
    private String[] tags;
    private int limit;
    private String category;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isAlcoholic() {
        return alcoholic;
    }

    public void setAlcoholic(boolean alcoholic) {
        this.alcoholic = alcoholic;
    }

    public boolean isNon_alcoholic() {
        return non_alcoholic;
    }

    public void setNon_alcoholic(boolean non_alcoholic) {
        this.non_alcoholic = non_alcoholic;
    }

    private boolean alcoholic = true;
    private boolean non_alcoholic = true;


}
