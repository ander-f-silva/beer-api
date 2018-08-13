package br.com.ciclic.brewery.beer.domain.service;

import br.com.ciclic.brewery.beer.application.transferobject.BeerStyleTransferObject;
import br.com.ciclic.brewery.beer.application.transferobject.BreweryTransferObject;
import br.com.ciclic.brewery.beer.application.transferobject.JukeBoxTransferObject;
import br.com.ciclic.brewery.beer.domain.adapter.BeerStyleAdapter;
import br.com.ciclic.brewery.beer.domain.decorator.BeerStyleDecorator;
import br.com.ciclic.brewery.beer.domain.entity.BeerStyle;
import br.com.ciclic.brewery.beer.domain.exception.EntityExistsException;
import br.com.ciclic.brewery.beer.domain.exception.NotFoundException;
import br.com.ciclic.brewery.beer.infrastructure.api.clien.rest.spotify.SpotifyClient;
import br.com.ciclic.brewery.beer.infrastructure.api.clien.rest.spotify.valueobject.Playlist;
import br.com.ciclic.brewery.beer.infrastructure.api.clien.rest.spotify.valueobject.PlaylistError;
import br.com.ciclic.brewery.beer.infrastructure.repository.BeerStyleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Service
public class BeerStyleService {

    @Autowired
    private BeerStyleRepository repository;

    @Autowired
    private SpotifyClient spotifyClient;

    public String add(BeerStyleTransferObject to) throws Exception {
        BeerStyle entity = repository.findByName(to.getName());
        if (entity != null) {
            throw new EntityExistsException("Beer style has already been registered.");
        }

        BeerStyleAdapter adapter = new BeerStyleAdapter(to);
        BeerStyle beerStyle = adapter.converterEntity();
        beerStyle = repository.insert(beerStyle);

        return beerStyle.getId();
    }

    public void edit(String id, BeerStyleTransferObject to) throws Exception {
        if (!repository.exists(id)) {
            throw new NotFoundException("The beer style not found.");
        }

        BeerStyle entity = repository.findByName(to.getName());
        if (entity != null && !entity.getId().equals(id)) {
            throw new EntityExistsException("Beer style has already been registered.");
        }

        BeerStyleAdapter adapter = new BeerStyleAdapter(to);
        BeerStyle beerStyle = adapter.converterEntity();
        beerStyle.setId(id);
        repository.save(beerStyle);
    }

    public void delete(String id) throws Exception {
        if (!repository.exists(id)) {
            throw new NotFoundException("The beer style not found.");
        }

        repository.delete(id);
    }

    public BeerStyleTransferObject find(String id) throws Exception {
        if (!repository.exists(id)) {
            throw new NotFoundException("The beer style not found.");
        }

        BeerStyle entity = repository.findOne(id);
        return new BeerStyleAdapter(entity).converterTransferObject();
    }

    public BreweryTransferObject findAll() throws Exception {
        List<BeerStyle> entities = repository.findAll();
        if (entities.isEmpty()) {
            throw new NotFoundException("The beer style not found.");
        }

        List<BeerStyleTransferObject> list = entities.stream()
                                                     .map( entity -> new BeerStyleAdapter(entity).converterTransferObject())
                                                     .collect(Collectors.toList());

        return new BreweryTransferObject(list);
    }

    public JukeBoxTransferObject findByTemperature(Integer temperature) throws Exception {
        List<BeerStyle> entities = repository.findAll();
        if (entities.isEmpty()) {
            throw new NotFoundException("The beer style not found.");
        }

        SortedSet<BeerStyle> entitiesSorted = new TreeSet<>(entities);

        BeerStyle entity = entitiesSorted.parallelStream()
                                    .map(e -> new BeerStyleDecorator(e, temperature))
                                    .collect(Collectors.toList())
                                    .stream()
                                    .min((d1, d2) -> Integer.compare(d1.getTemperatureDifference(), d2.getTemperatureDifference()))
                                    .map(d -> d.getBeerStyle())
                                    .get();

        Playlist playlist = spotifyClient.findPlaylistsTracks(entity.getName());

        if (playlist == null) {
            new JukeBoxTransferObject(entity.getName(), new PlaylistError(404, "Not found playlist."));
        }

        return new JukeBoxTransferObject(entity.getName(), playlist);
    }

}
