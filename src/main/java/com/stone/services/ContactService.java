package com.stone.services;

import com.stone.dtos.CallListDto;
import com.stone.dtos.ContactDto;
import com.stone.dtos.PhoneDto;
import com.stone.entities.ContactEntity;
import com.stone.entities.PhoneEntity;
import com.stone.exceptions.EntityNotFoundException;
import com.stone.repositories.ContactRepository;
import com.stone.utils.ContactMapper;
import com.stone.utils.ContactUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ContactService {


    @Autowired
    private ContactRepository contactRepository;

    public void deleteEntity(Long id) {
        contactRepository.deleteById(id);
    }

    public Set<ContactDto> getAllEntities() {

        List<ContactEntity> contactDtos = contactRepository.findAll();

        if (contactDtos == null) {
            throw new EntityNotFoundException();
        }
        return contactDtos.stream().map(d -> ContactMapper.mapContactFromEntity(d)).collect(Collectors.toSet());

    }

    public Set<CallListDto> getCallList() {

        List<ContactEntity> contacts = contactRepository.findAll();

        if (contacts == null) {
            throw new EntityNotFoundException();
        }

        return contacts.stream().filter(t -> containsHomePhone(t.getPhone())).
                map(c -> ContactMapper.mapCallListDto.apply(c)).
                collect(Collectors.toSet());
    }

    public ContactDto getContactById(Long id) {
        Optional<ContactEntity> resp = contactRepository.findById(id);
        if (!resp.isPresent()) {
            throw new EntityNotFoundException();
        }
        return ContactMapper.mapContactFromEntity(resp.get());
    }

    public ContactDto updateContact(Long id, ContactDto contactDetails) {
        ContactUtils.validatePhone(contactDetails);
        ContactDto resp = getContactById(id);
        updateContact(contactDetails, resp);
        ContactEntity contactEntity = ContactMapper.contactDtotoEntity.apply(resp);
        return ContactMapper.mapContactFromEntity(contactRepository.save(contactEntity));

    }

    public ContactDto saveContact(ContactDto contactDetails) {
        ContactUtils.validatePhone(contactDetails);
        ContactEntity contact = ContactMapper.contactDtotoEntity.apply(contactDetails);
        return ContactMapper.mapContactFromEntity(contactRepository.save(contact));

    }


    public void updateContact(ContactDto source, ContactDto dest) {

        if (!StringUtils.isEmpty(source.getEmail())) {
            dest.setEmail(source.getEmail());
        }

        if (source.getAddress() != null) {
            if (dest.getAddress() == null) {
                dest.setAddress(source.getAddress());
            } else {
                if (!StringUtils.isEmpty(source.getAddress().getStreet())) {
                    dest.getAddress().setStreet(source.getAddress().getStreet());
                }
                if (!StringUtils.isEmpty(source.getAddress().getState())) {
                    dest.getAddress().setState(source.getAddress().getState());
                }
                if (!StringUtils.isEmpty(source.getAddress().getZip())) {
                    dest.getAddress().setZip(source.getAddress().getZip());
                }
                if (!StringUtils.isEmpty(source.getAddress().getCity())) {
                    dest.getAddress().setCity(source.getAddress().getCity());
                }
            }
        }

        if (source.getName() != null) {
            if (dest.getName() == null) {
                dest.setName(source.getName());
            } else {
                if (!StringUtils.isEmpty(source.getName().getFirst())) {
                    dest.getName().setFirst(source.getName().getFirst());
                }
                if (!StringUtils.isEmpty(source.getName().getMiddle())) {
                    dest.getName().setMiddle(source.getName().getMiddle());
                }
                if (!StringUtils.isEmpty(source.getName().getLast())) {
                    dest.getName().setLast(source.getName().getLast());
                }
            }
        }

        if (source.getPhone() != null) {
            if (dest.getPhone() == null) {
                dest.setPhone(source.getPhone());
            } else {
                Set<PhoneDto> phone = source.getPhone();
                for (PhoneDto p : phone) {
                    PhoneDto oldPhone = ContactUtils.getPhoneFromType(dest.getPhone(), p.getType());
                    if (oldPhone != null) {
                        oldPhone.setNumber(p.getNumber());
                    } else {
                        dest.getPhone().add(p);
                    }

                }
            }
        }

    }

    public boolean containsHomePhone(Set<PhoneEntity> phone) {
        return phone.stream().filter(t -> "home".equals(t.getType())).count() != 0;
    }


}

