package com.zenika.meetingplanner.services;

import com.zenika.meetingplanner.DTOs.ReunionRequest;
import com.zenika.meetingplanner.DTOs.SalleRequest;
import com.zenika.meetingplanner.DTOs.SalleResponse;
import com.zenika.meetingplanner.entities.Reservation;
import com.zenika.meetingplanner.entities.Salle;
import com.zenika.meetingplanner.enums.ReunionType;
import com.zenika.meetingplanner.exceptions.InvalidReservationTimeException;
import com.zenika.meetingplanner.exceptions.NoRoomsAvailableException;
import com.zenika.meetingplanner.repositories.SalleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class SalleServiceImpl implements SalleService {


    private final SalleRepository salleRepository;
    private final ReservationService reservationService;

    public SalleServiceImpl(SalleRepository salleRepository,
                            ReservationService reservationService) {
        this.salleRepository = salleRepository;
        this.reservationService = reservationService;
    }

    @Override
    public List<SalleResponse> recommendSallesByReunion(ReunionRequest reunionRequest) {
        int roomCapacity = reunionRequest.getGuestsNum() * 100/70;
        LocalDateTime time = LocalDateTime.parse(reunionRequest.getTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        ReunionType type = reunionRequest.getType();
        List<SalleResponse> salleResponses = new ArrayList<>();

        if (time.getHour() >= 8 && time.getHour() <= 20 && time.getMinute() == 0) {
            if (type == ReunionType.RC) {
                for (Salle salle : findSallesForRC(roomCapacity, time)) {
                    salleResponses.add(SalleResponse.toSalleResponse(salle));
                }
            }
            else if (type == ReunionType.RS) {
                for (Salle salle : findSallesForRS(roomCapacity, time)) {
                    salleResponses.add(SalleResponse.toSalleResponse(salle));
                }
            }
            else if (type == ReunionType.VC) {
                for (Salle salle : findSallesForVC(roomCapacity, time)) {
                    salleResponses.add(SalleResponse.toSalleResponse(salle));
                }
            }
            else if (type == ReunionType.SPEC) {
                for (Salle salle : findSallesForSPEC(roomCapacity, time)) {
                    salleResponses.add(SalleResponse.toSalleResponse(salle));
                }
            }
        }
        else {
            throw new InvalidReservationTimeException("Rooms should be reserved between 8h00 and 20h00 only");
        }
        return salleResponses;
    }


    @Override
    public SalleResponse addSalle(SalleRequest salleRequest) {
        if(salleRepository.existsByName(salleRequest.getName()))
            throw new NameAlreadyExists("Name already exists");
        return SalleResponse.toSalleResponse(salleRepository.save(Salle.toSalle(salleRequest)));
    }

    @Override
    public List<SalleResponse> getAllSalles() {
        List<SalleResponse> salleResponses = new ArrayList<>();
        List<Salle> salles = salleRepository.findAll();
        for (Salle salle : salles) {
            salleResponses.add(SalleResponse.toSalleResponse(salle));
        }
        return salleResponses;
    }


    List<Salle> findSallesForRS(int roomCapacity, LocalDateTime time) {
        List<Salle> salles = new ArrayList<>();
        salles = salleRepository.findByMaxCapacityGreaterThan(roomCapacity);

        if (salles.isEmpty())
            throw new NoRoomsAvailableException("0 room available");

        Iterator<Salle> iterator = salles.iterator();
        while (iterator.hasNext()) {
            Salle salle = iterator.next();
            Reservation reservation = reservationService.findReservation(time, time.plusHours(2L), salle.getSalleId());
            if (reservation != null) {
                iterator.remove(); // Remove the current element using the iterator
            }
        }
        if (salles.isEmpty())
            throw new NoRoomsAvailableException("0 room available");

        return salles;
    }

    List<Salle> findSallesForRC(int roomCapacity, LocalDateTime time) {
        List<Salle> allSalles = salleRepository.findByMaxCapacityGreaterThan(roomCapacity);

        List<Salle> filteredSalles = allSalles.stream()
                .filter(salle -> (salle.isHasScreen())
                        && (salle.isHasWhiteboard())
                        && (salle.isHasPieuvre()))
                .toList();

        if (filteredSalles.isEmpty()) {
            filteredSalles = allSalles.stream()
                    .filter(salle -> (salle.isHasScreen() && salle.isHasWhiteboard()) ||
                            (salle.isHasScreen() && salle.isHasPieuvre()) ||
                            (salle.isHasWhiteboard() && salle.isHasPieuvre()))
                    .toList();
        }

        List<Salle> salles = new ArrayList<>(filteredSalles);

        if (salles.isEmpty())
            throw new NoRoomsAvailableException("0 room available");

        Iterator<Salle> iterator = salles.iterator();
        while (iterator.hasNext()) {
            Salle salle = iterator.next();
            Reservation reservation = reservationService.findReservation(time, time.plusHours(2L), salle.getSalleId());
            if (reservation != null) {
                iterator.remove(); // Remove the current element using the iterator
            }
        }
        if (salles.isEmpty())
            throw new NoRoomsAvailableException("0 room available");

        return salles;
    }

    private List<Salle> findSallesForSPEC(int roomCapacity, LocalDateTime time) {
        List<Salle> allSalles = salleRepository.findByMaxCapacityGreaterThan(roomCapacity);

        List<Salle> filteredSalles = allSalles.stream()
                .filter(Salle::isHasWhiteboard)
                .toList();

        List<Salle> salles = new ArrayList<>(filteredSalles);

        if (salles.isEmpty())
            throw new NoRoomsAvailableException("0 room available");

        Iterator<Salle> iterator = salles.iterator();
        while (iterator.hasNext()) {
            Salle salle = iterator.next();
            Reservation reservation = reservationService.findReservation(time, time.plusHours(2L), salle.getSalleId());
            if (reservation != null) {
                iterator.remove(); // Remove the current element using the iterator
            }
        }
        if (salles.isEmpty())
            throw new NoRoomsAvailableException("0 room available");

        return salles;
    }

    private List<Salle> findSallesForVC(int roomCapacity, LocalDateTime time) {

        List<Salle> allSalles = salleRepository.findByMaxCapacityGreaterThan(roomCapacity);

        List<Salle> filteredSalles = allSalles.stream()
                .filter(salle -> (salle.isHasScreen())
                        && (salle.isHasWebcam())
                        && (salle.isHasPieuvre()))
                .toList();

        if (filteredSalles.isEmpty()) {
            filteredSalles = allSalles.stream()
                    .filter(salle -> (salle.isHasScreen() && salle.isHasWebcam()) ||
                            (salle.isHasScreen() && salle.isHasPieuvre()) ||
                            (salle.isHasWebcam() && salle.isHasPieuvre()))
                    .toList();
        }

        List<Salle> salles = new ArrayList<>(filteredSalles);

        if (salles.isEmpty())
            throw new NoRoomsAvailableException("0 room available");

        Iterator<Salle> iterator = salles.iterator();
        while (iterator.hasNext()) {
            Salle salle = iterator.next();
            Reservation reservation = reservationService.findReservation(time, time.plusHours(2L), salle.getSalleId());
            if (reservation != null) {
                iterator.remove(); // Remove the current element using the iterator
            }
        }
        if (salles.isEmpty())
            throw new NoRoomsAvailableException("0 room available");

        return salles;
    }


}
