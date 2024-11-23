package com.teachCode.ecommerce1.repositories;

import com.teachCode.ecommerce1.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

//    une requête SQL native (nativeQuery = true), ce qui signifie qu'elle est directement
//    exécutée dans le langage SQL spécifique à la base de données (plutôt que du JPQL ou HQL).

//    Le champ user_id de la notification est égal à l'ID passé en paramètre (n.user_id = ?1).
//    Ou le champ user_id est une chaîne vide (n.user_id = '').
//    Cela signifie que la requête récupère soit toutes les notifications
//    appartenant à un utilisateur spécifique (user_id = ?1),
//    soit les notifications qui n'ont pas d'utilisateur assigné
//    (user_id = '', qui pourrait signifier une notification générique).
    @Query(
            nativeQuery = true,
            value = "SELECT * FROM notifications n WHERE n.user_id = ?1 OR n.user_id = ''"
    )
//    Optional : La méthode renvoie un Optional, ce qui signifie
//    qu'il est possible que la liste soit vide (aucune notification ne correspond aux critères).
    Optional<List<Notification>> findByIdAndByAll(String id);
}
