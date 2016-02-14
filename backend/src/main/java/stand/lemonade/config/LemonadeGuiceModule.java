package stand.lemonade.config;

import com.google.inject.AbstractModule;

import stand.lemonade.dal.CostDal;
import stand.lemonade.dal.CostDalImpl;
import stand.lemonade.dal.IncomeDal;
import stand.lemonade.dal.IncomeDalImpl;
import stand.lemonade.dal.ProfileImageDal;
import stand.lemonade.dal.ProfileImageDalImpl;
import stand.lemonade.dal.ShiftDal;
import stand.lemonade.dal.ShiftDalImpl;
import stand.lemonade.dal.UserDal;
import stand.lemonade.dal.UserDalImpl;
import stand.lemonade.dal.WalletDal;
import stand.lemonade.dal.WalletDalImpl;
import stand.lemonade.database.DatabaseDalTest;
import stand.lemonade.database.DatabaseDalTestImpl;
import stand.lemonade.service.CostService;
import stand.lemonade.service.CostServiceImpl;
import stand.lemonade.service.HistoryService;
import stand.lemonade.service.HistoryServiceImpl;
import stand.lemonade.service.IncomeService;
import stand.lemonade.service.IncomeServiceImpl;
import stand.lemonade.service.ProfileImageService;
import stand.lemonade.service.ProfileImageServiceImpl;
import stand.lemonade.service.ShiftService;
import stand.lemonade.service.ShiftServiceImpl;
import stand.lemonade.service.UserService;
import stand.lemonade.service.UserServiceImpl;
import stand.lemonade.service.WalletService;
import stand.lemonade.service.WalletServiceImpl;


public class LemonadeGuiceModule extends AbstractModule {

	@Override
	protected void configure() {

		bind(WalletService.class).to(WalletServiceImpl.class);
		bind(WalletDal.class).to(WalletDalImpl.class);

		bind(ShiftService.class).to(ShiftServiceImpl.class);
		bind(ShiftDal.class).to(ShiftDalImpl.class);

		bind(UserService.class).to(UserServiceImpl.class);
		bind(UserDal.class).to(UserDalImpl.class);

		bind(IncomeService.class).to(IncomeServiceImpl.class);
		bind(IncomeDal.class).to(IncomeDalImpl.class);

		bind(CostService.class).to(CostServiceImpl.class);
		bind(CostDal.class).to(CostDalImpl.class);
		
		bind(ProfileImageDal.class).to(ProfileImageDalImpl.class);
		bind(ProfileImageService.class).to(ProfileImageServiceImpl.class);

		bind(HistoryService.class).to(HistoryServiceImpl.class);

		bind(DatabaseDalTest.class).to(DatabaseDalTestImpl.class);

	}

}
