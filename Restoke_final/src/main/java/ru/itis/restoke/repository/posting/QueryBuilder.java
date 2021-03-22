package ru.itis.restoke.repository.posting;

public class QueryBuilder {
    String query;
    boolean hasConditions;

    public QueryBuilder() {
        query = "SELECT * FROM postings " +
                "JOIN photo p on postings.id = p.posting_id " +
                "JOIN sellers s on postings.seller_id = s.id";
    }

    public QueryBuilder addRoleCondition(String role) {
        startNewCondition();
        query = query +  "(role = " + role + ")";
        return this;
    }

    public QueryBuilder addMinPriceCondition(int minPrice) {
        startNewCondition();
        query = query + "(price >= " + minPrice + ")";
        return this;
    }

    public QueryBuilder addMaxPriceCondition(int maxPrice) {
        startNewCondition();
        query = query + "(price <= " + maxPrice + ")";
        return this;
    }

    public QueryBuilder addCityCondition(String city) {
        startNewCondition();
        query = query + "(city = " + city + ")";
        return this;
    }

    public QueryBuilder addSearchCondition(String[] words) {
        startNewCondition();
        for (int i = 0; i < words.length; i++) {
            if ( i != 0)
                query = query + " AND";
            // Добавить одинарные кавычки // где?
            query = query + " (header ILIKE '%" + words[i] + "%' or description ILIKE '%" + words[i] + "%')";
        }
        return this;
    }

    public QueryBuilder addPostingIdCondition(long id) {
        startNewCondition();
        query = query + "(postings.id = " + id + ")";
        return this;
    }

    public QueryBuilder addSubcategoryCondition(Long subcategoryId) {
        startNewCondition();
        query = query + "(subcategory_id = " + subcategoryId + ")";
        return this;
    }

    private void startNewCondition() {
        if (!hasConditions) {
            query = query + " WHERE ";
            hasConditions = true;
        }
        else
            query = query + " AND ";
    }

    public String build() {
        return query + ";";
    }
}
