package ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ems.models.*;

@Repository
interface VersionRepository extends JpaRepository<Version, Long> {}

@Repository
interface MetadataRepository extends JpaRepository<Metadata, Long> {}

@Repository
interface PowerRepository extends JpaRepository<Power, Long> {}

@Repository
interface StateRepository extends JpaRepository<State, Long> {}

@Repository
interface App_TimeRepository extends JpaRepository<App_Time, Long> {}

@Repository
interface App_EventRepository extends JpaRepository<App_Event, Long> {}

@Repository
interface Top_I2c_ErrorsRepository extends JpaRepository<Top_I2c_Errors, Long> {}

@Repository
interface M_I2c_ErrorsRepository extends JpaRepository<M_I2c_Errors, Long> {}

@Repository
interface Af_ErrorsRepository extends JpaRepository<Af_Errors, Long> {}

@Repository
interface Pz_ErrorsRepository extends JpaRepository<Pz_Errors, Long> {}

@Repository
interface Us_ErrorsRepository extends JpaRepository<Us_Errors, Long> {}

@Repository
interface Modem_ErrorsRepository extends JpaRepository<Modem_Errors, Long> {}

@Repository
interface Heater_ErrorsRepository extends JpaRepository<Heater_Errors, Long> {}

@Repository
interface Pneumatic_ErrorsRepository extends JpaRepository<Pneumatic_Errors, Long> {}

@Repository
interface Monitor_ErrorsRepository extends JpaRepository<Monitor_Errors, Long> {}

@Repository
interface Satellite_ErrorsRepository extends JpaRepository<Satellite_Errors, Long> {}

@Repository
interface Adc_ErrorsRepository extends JpaRepository<Adc_Errors, Long> {}

@Repository
interface Other_ErrorsRepository extends JpaRepository<Other_Errors, Long> {}
