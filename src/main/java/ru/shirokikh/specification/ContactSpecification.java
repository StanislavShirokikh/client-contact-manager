package ru.shirokikh.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import ru.shirokikh.dto.ContactFilter;
import ru.shirokikh.entity.Contact;
import ru.shirokikh.entity.ContactType;

@RequiredArgsConstructor
public class ContactSpecification implements Specification<Contact> {
    private final ContactFilter contactFilter;

    @Override
    public Predicate toPredicate(Root<Contact> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Predicate predicate = criteriaBuilder.conjunction();

        if (contactFilter.getClientId() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("client").get("id"), contactFilter.getClientId()));
        }

        if (contactFilter.getContactType() != null && !contactFilter.getContactType().isEmpty()) {
            ContactType contactType = ContactType.valueOf(contactFilter.getContactType().toUpperCase());  // Преобразуем строку в Enum
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("type"), contactType));
        }

        return predicate;
    }
}
