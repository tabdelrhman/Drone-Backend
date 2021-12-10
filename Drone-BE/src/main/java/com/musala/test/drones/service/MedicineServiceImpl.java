package com.musala.test.drones.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.musala.test.drones.entities.Medicine;
import com.musala.test.drones.entities.MedicinePhoto;
import com.musala.test.drones.execption.DroneValidationException;
import com.musala.test.drones.repository.MedicinePhotoRepository;
import com.musala.test.drones.repository.MedicineRepository;

@Component
public class MedicineServiceImpl implements MedicineService {

	@Autowired
	MedicineRepository medicineRepo;

	@Autowired
	MedicinePhotoRepository medicinePhotoRepository;

	@Override
	public Medicine addNewMedicine(Medicine medicine) {
		return medicineRepo.save(medicine);
	}

	@Override
	public Medicine addMedicinceWithPhoto(MultipartFile file, String code, String name, int weight) throws IOException {

		Medicine medicine = new Medicine();

		medicine.setCode(code);
		medicine.setName(name);
		medicine.setWeight(weight);

		doSomeChecksBeforeAdding(medicine);

		MedicinePhoto medicincePhoto = new MedicinePhoto(file.getOriginalFilename(), file.getContentType(),
				compressBytes(file.getBytes()));

		medicincePhoto = medicinePhotoRepository.save(medicincePhoto);

		medicine.setMedicinePhoto(medicincePhoto);

		medicineRepo.save(medicine);
		return medicine;
	}

	private void doSomeChecksBeforeAdding(Medicine medicine) {

		Pattern p;
		Matcher m;
		// check for medicine name contains only letters,number, - or _
		p = Pattern.compile("[\\-_]*[a-z]*[0-9]*[a-z]*[0-9]*[\\-_]*", Pattern.CASE_INSENSITIVE);
		m = p.matcher(medicine.getName());
		if (!m.matches())
			throw new DroneValidationException("Error: Medicine name should contains only letters,number, - or _",
					HttpStatus.BAD_REQUEST);

		// check for medicine code contains only letters,number, or _
		p = Pattern.compile("[\\-_]*[A-Z]*[0-9]*[A-Z]*[0-9]*[\\_]*");
		m = p.matcher(medicine.getCode());
		if (!m.matches())
			throw new DroneValidationException(
					"Error: Medicine code should contains only uppercase letters,number, - or _",
					HttpStatus.BAD_REQUEST);
	}

	public static byte[] compressBytes(byte[] data) {
		Deflater deflater = new Deflater();
		deflater.setInput(data);
		deflater.finish();

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		while (!deflater.finished()) {
			int count = deflater.deflate(buffer);
			outputStream.write(buffer, 0, count);
		}
		try {
			outputStream.close();
		} catch (IOException e) {
		}
		System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

		return outputStream.toByteArray();
	}

	@Override
	public MedicinePhoto getMedicinePhoto(String photoName) {

		final Optional<MedicinePhoto> retrievedPhoto = medicinePhotoRepository.findByName(photoName);
		MedicinePhoto img = new MedicinePhoto(retrievedPhoto.get().getName(), retrievedPhoto.get().getType(),
				decompressBytes(retrievedPhoto.get().getPicByte()));
		return img;
	}

	public static byte[] decompressBytes(byte[] data) {
		Inflater inflater = new Inflater();
		inflater.setInput(data);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		try {
			while (!inflater.finished()) {
				int count = inflater.inflate(buffer);
				outputStream.write(buffer, 0, count);
			}
			outputStream.close();
		} catch (IOException ioe) {
		} catch (DataFormatException e) {
		}
		return outputStream.toByteArray();
	}

	@Override
	public List<Medicine> getAllMedicines() {
		// return medicineRepo.findAll();

		List<Medicine> allMedicine = new ArrayList<Medicine>();
		Medicine medicine;
		Iterator<Medicine> iterator = medicineRepo.findAll().iterator();
		while (iterator.hasNext()) {
			medicine = iterator.next();
			medicine.getMedicinePhoto().setPicByte(decompressBytes(medicine.getMedicinePhoto().getPicByte()));
			allMedicine.add(medicine);
		}
		return allMedicine;
	}
}
