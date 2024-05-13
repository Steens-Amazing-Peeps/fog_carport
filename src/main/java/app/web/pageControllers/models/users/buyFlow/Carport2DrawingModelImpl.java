package app.web.pageControllers.models.users.buyFlow;

import app.web.entities.Carport;
import app.web.exceptions.WebInvalidInputException;
import app.web.services.SvgCarport;

public class Carport2DrawingModelImpl implements Carport2DrawingModel
{
    @Override
    public String drawCarport(Carport carport) throws WebInvalidInputException {
        SvgCarport svgCarport = new SvgCarport(carport);

        return svgCarport.drawCarport();
    }
}
