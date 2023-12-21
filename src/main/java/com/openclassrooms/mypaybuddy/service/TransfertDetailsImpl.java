package com.openclassrooms.mypaybuddy.service;


import com.openclassrooms.mypaybuddy.model.Compte;
import com.openclassrooms.mypaybuddy.model.Transaction;
import com.openclassrooms.mypaybuddy.repository.CompteRepository;
import com.openclassrooms.mypaybuddy.repository.TransactionRepository;
//import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

//import static java.awt.Container.log;

//import static java.awt.Container.log;


@Service
@Transactional
public class TransfertDetailsImpl implements ITransfert {
    @Autowired
    CompteRepository compteRepository;
    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public void transfer(Compte compteEmetteur, Compte compteRecepteur, double montant) {
       try {
           compteEmetteur.retrait(montant);
           compteRecepteur.depot(montant);
           compteRepository.save(compteEmetteur);
           compteRepository.save(compteRecepteur);
       }catch (Exception e){
           System.out.println("erreur de la méthode transfert" +e);
         //   log.error("erreur de la méthode transfert" +e);
       }
    }

    @Override
    public Transaction saveTransfert(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction getTransactionById(Long id) {
        return transactionRepository.findById(Math.toIntExact(id)).get();
    }

    public Iterable<Transaction> getTransaction(){
        return transactionRepository.findAll();
    }

    public Optional<Transaction> getTransactionById(Integer id){
        return  transactionRepository.findById(id);
    }
    public Transaction addTransaction(Transaction  transaction){
        return transactionRepository.save(transaction);
    }

}
