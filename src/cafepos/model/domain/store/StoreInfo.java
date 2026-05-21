package cafepos.model.domain.store;

public class StoreInfo {
    private final String storeName;
    private final String ownerName;
    private final String businessNumber;
    private final String telephone;
    private final String address;

    public StoreInfo(String storeName, String ownerName, String businessNumber, String telephone, String address) {
        this.storeName = storeName;
        this.ownerName = ownerName;
        this.businessNumber = businessNumber;
        this.telephone = telephone;
        this.address = address;
    }

    public String getStoreName() { return storeName; }
    public String getOwnerName() { return ownerName; }
    public String getBusinessNumber() { return businessNumber; }
    public String getTelephone() { return telephone; }
    public String getAddress() { return address; }

    public static StoreInfo Store() {
        return new StoreInfo(
                "KTB4 Fullstack Cafe",
                "neo.jugn",
                "123456789",
                "02-XXXX-XXXX",
                "경기도 성남시 분당구 판고역로 166"
        );
    }
}
